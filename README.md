# devxp-elhub-gradle

[<img src="https://img.shields.io/badge/repo-github-blue" alt=""/>](https://github.com/elhub/devxp-elhub-gradle)
[<img src="https://img.shields.io/badge/issues-jira-orange" alt=""/>](https://jira.elhub.cloud/issues/?jql=project%20%3D%20%22Team%20Dev%22%20AND%20component%20%3D%20devxp-elhub-gradle%20AND%20status%20!%3D%20Done)
[<img src="https://teamcity.elhub.cloud/app/rest/builds/buildType:(id:DexXp_DexXpElhubGradle_AutoRelease)/statusIcon" alt=""/>](https://teamcity.elhub.cloud/project/DexXp_DexXpElhubGradle?mode=builds#all-projects)
[<img src="https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.devxp%3Adevxp-elhub-gradle&metric=alert_status" alt=""/>](https://sonar.elhub.cloud/dashboard?id=no.elhub.devxp%3Adevxp-elhub-gradle)
[<img src="https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.devxp%3Adevxp-elhub-gradle&metric=ncloc" alt=""/>](https://sonar.elhub.cloud/dashboard?id=no.elhub.devxp%3Adevxp-elhub-gradle)
[<img src="https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.devxp%3Adevxp-elhub-gradle&metric=bugs" alt=""/>](https://sonar.elhub.cloud/dashboard?id=no.elhub.devxp%3Adevxp-elhub-gradle)
[<img src="https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.devxp%3Adevxp-elhub-gradle&metric=vulnerabilities" alt=""/>](https://sonar.elhub.cloud/dashboard?id=no.elhub.devxp%3Adevxp-elhub-gradle)
[<img src="https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.devxp%3Adevxp-elhub-gradle&metric=coverage" alt=""/>](https://sonar.elhub.cloud/dashboard?id=no.elhub.devxp%3Adevxp-elhub-gradle)

## About

**DEPRECATED**

This was the custom gradle distribution used by Elhub gradle projects. Having a custom gradle distribution allows us to 
wrap common logic that is used in most or all projects into a single package and reuse this in many projects.

Support for init scripts in gradle is not great, though, and we found multiple things we wanted to do were either difficult or in pracitce impossible to carry out in kts scripts. Consequently, we decided to use [gradle plugins instead](https://github.com/elhub/devxp-elhub-gradle-plugins).

## Getting Started

### Prerequisites

This project uses the standard gradle distribution.

### Installation

To build the custom distribution:

```sh
./gradlew assemble
```

To publish:
```sh
./gradlew publish
```

## Usage

To make use of elhub-gradle within Elhub, edit your gradle/wrapper/gradle-wrapper.properties file and change your
distributionUrl to:
```
distributionUrl=https\://jfrog.elhub.cloud/artifactory/elhub-bin/elhub-gradle/<version>/elhub-gradle-<version>.zip
```

The above assumes access to our jfrog server, of course; if you don't have that, you need to deploy the project to
a package server and retrieve it from there.

The benefit for an Elub gradle project, is that you can delete all the boilerplate gradle stuff (around
70-80% of a typical build.gradle.kts file), and have them solely consist of identity information (group,
name, version, description) and the list of dependencies.

See the minimal example project in `src/test/resources`.

## Testing

Run `./gradlew test` which attempts to apply the init.gradle script on the example project in src/test/resources.

## Contributing

Contributing, issues and feature requests are welcome. See the
[Contributing](https://github.com/elhub.test-konfig/blob/main/CONTRIBUTING.md) file.

## Owners

This project is developed by [Elhub](https://github.com/elhub). For the specific development group responsible for this
code, see the [Codeowners](https://github.com/elhub/devxp-elhub-gradle/blob/main/CODEOWNERS) file.

## License

This project is [MIT](https://github.com/elhub/devxp-elhub-gradle/blob/main/LICENSE.md) licensed.
