import jetbrains.buildServer.configs.kotlin.v2019_2.version
import jetbrains.buildServer.configs.kotlin.v2019_2.project
import jetbrains.buildServer.configs.kotlin.v2019_2.BuildType
import jetbrains.buildServer.configs.kotlin.v2019_2.CheckoutMode
import jetbrains.buildServer.configs.kotlin.v2019_2.DslContext
import jetbrains.buildServer.configs.kotlin.v2019_2.ProjectFeature
import jetbrains.buildServer.configs.kotlin.v2019_2.ProjectFeatures
import jetbrains.buildServer.configs.kotlin.v2019_2.Template
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.VcsTrigger
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot
import no.elhub.common.build.configuration.CreateExeGradle
import no.elhub.common.build.configuration.UnitTestGradle

val projectId = "dev-tools-elhub-gradle"
version = "2020.2"

project {

    params {
        param("teamcity.ui.settings.readOnly", "true")
    }

    val buildChain = sequential {

        buildType(
            SonarScan(
                SonarScan.Config(
                    id = "SonarScan",
                    name = "Code Analysis",
                    vcsRoot = DslContext.settingsRoot,
                    sonarId = projectId,
                    sonarProjectSources = "app"
                )
            )
        )

        buildType(
            object Build : BuildType({
                id = "Assemble"
                name = "Assemble"

                vcs {
                    root(DslContext.settingsRoot)
                }

                steps {
                    gradle {
                        name = "Assemble jar file"
                        tasks = "buildDist"
                        buildFile = "build.gradle.kts"
                    }
                    gradle {
                        name = "Publish to Artifactory"
                        tasks = "artifactoryPublish"
                        buildFile = "build.gradle.kts"
                        param("org.jfrog.artifactory.selectedDeployableServer.projectUsesArtifactoryGradlePlugin", "true")
                        param("org.jfrog.artifactory.selectedDeployableServer.useM2CompatiblePatterns", "false")
                        param("org.jfrog.artifactory.selectedDeployableServer.publishBuildInfo", "true")
                        param("org.jfrog.artifactory.selectedDeployableServer.defaultModuleVersionConfiguration", "GLOBAL")
                        param("org.jfrog.artifactory.selectedDeployableServer.urlId", "0")
                        param("org.jfrog.artifactory.selectedDeployableServer.envVarsExcludePatterns", "*password*,*secret*")
                        param("org.jfrog.artifactory.selectedDeployableServer.publishMavenDescriptors", "true")
                        param("org.jfrog.artifactory.selectedDeployableServer.deployArtifacts", "true")
                        param("org.jfrog.artifactory.selectedDeployableServer.activateGradleIntegration", "true")
                        param("org.jfrog.artifactory.selectedDeployableServer.targetRepo", "elhub-bin-release-local")
                    }
                }

                triggers {
                    vcs {
                    }
                }
            })
        )

    }

    buildChain.buildTypes().forEach { buildType(it) }
}
