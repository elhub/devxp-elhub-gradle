import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.jfrog.gradle.plugin.artifactory.ArtifactoryPlugin
import org.jfrog.gradle.plugin.artifactory.dsl.ArtifactoryPluginConvention
import org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig
import org.jfrog.gradle.plugin.artifactory.task.ArtifactoryTask
import groovy.lang.GroovyObject

plugins {
    id("tech.harmonysoft.oss.custom-gradle-dist-plugin") version "1.7"
    id("com.github.ben-manes.versions") version "0.36.0"
    id("com.jfrog.artifactory") version "4.18.3"
    id("maven-publish") apply true
}

val gradleDistVersion : String by project
val mavenPubName = "gradleDistribution"

gradleDist {
    gradleVersion = gradleDistVersion.toString()
    customDistributionVersion = version.toString()
    customDistributionName = rootProject.name
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r|-jre)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version)
    }
}

tasks.register<Sync>("buildDist") {
    dependsOn("build")
    from(file("$buildDir/gradle-dist/gradle-$gradleDistVersion-${rootProject.name}-$version.zip"))
    into(file("$buildDir/gradle-dist/"))
    rename("gradle-$gradleDistVersion-(.+)", "$1")
}

publishing {
    publications {
        create<MavenPublication>(mavenPubName) {
            artifact(file("$buildDir/gradle-dist/${rootProject.name}-$version.zip"))
            artifactId = rootProject.name
            version = version.toString()
        }
    }
}

fun Project.artifactory(configure: ArtifactoryPluginConvention.() -> Unit): Unit =
    configure(project.convention.getPluginByName<ArtifactoryPluginConvention>("artifactory"))

artifactory {
    publish(delegateClosureOf<PublisherConfig> {
        defaults(delegateClosureOf<GroovyObject> {
            invokeMethod("publications", mavenPubName)
            setProperty("publishArtifacts", true)
            setProperty("publishPom", false)
        })
    })
}
