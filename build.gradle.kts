import groovy.lang.GroovyObject
import org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig

plugins {
    id("tech.harmonysoft.oss.custom-gradle-dist-plugin") version "1.7"
    id("com.github.ben-manes.versions") version "0.38.0"
    id("com.jfrog.artifactory") version "4.21.0"
    id("maven-publish") apply true
}

val gradleDistVersion: String by project
val mavenPubName = "gradleDistribution"

gradleDist {
    gradleVersion = gradleDistVersion
    customDistributionVersion = version.toString()
    gradleDistributionType = "bin"
    customDistributionName = rootProject.name
}

tasks.register<Sync>("buildDist") {
    dependsOn("build")
    from(file("$buildDir/gradle-dist/gradle-$gradleDistVersion-${rootProject.name}-$version.zip"))
    into(file("$buildDir/gradle-dist/"))
    rename("gradle-$gradleDistVersion-(.+)", "$1")
}

tasks.create("assemble").dependsOn(tasks.get("buildDist"))

/*
 * Testing
 */
tasks.register("test", Exec::class) {
    description = "Tests the init.gradle script"
    workingDir("src/test/resources")
    commandLine("./gradlew", "--init-script", "../../main/resources/init.d/init.gradle", "tasks", "--all")
}

tasks.register("check") {
    dependsOn(tasks.get("test"))
}

tasks.register("jacocoTestReport")

/*
 * Clean
 */
tasks.register("clean", Delete::class)

/*
 * Publishing
 */
publishing {
    publications {
        create<MavenPublication>(mavenPubName) {
            artifact(file("$buildDir/gradle-dist/${rootProject.name}-$version.zip"))
            artifactId = rootProject.name
            version = version.toString()
        }
    }
}

artifactory {
    setContextUrl("https://jfrog.elhub.cloud/artifactory")
    publish(delegateClosureOf<PublisherConfig> {
        repository(delegateClosureOf<GroovyObject> {
            setProperty("repoKey", project.findProperty("artifactoryRepository") ?: "elhub-bin-dev-local")
            setProperty("username", project.findProperty("artifactoryUser") ?: "nouser")
            setProperty("password", project.findProperty("artifactoryPassword") ?: "nopass")
        })
        defaults(delegateClosureOf<GroovyObject> {
            invokeMethod("publications", mavenPubName)
            setProperty("publishArtifacts", true)
            setProperty("publishPom", false)
        })
    })
    resolve(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.ResolverConfig> {
        setProperty("repoKey", "repo")
    })
}

tasks.get("artifactoryPublish").dependsOn(tasks.get("assemble"))

tasks.get("publish").dependsOn(tasks.get("artifactoryPublish"))

/*
 * TeamCity
 */
tasks.register("teamCity", Exec::class) {
    description = "Compile the TeamCity settings"
    workingDir(".teamcity")
    commandLine("mvn", "compile")
}
