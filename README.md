# elhub-gradle

<!-- PROJECT SHIELDS -->
![TeamCity Build](https://teamcity.elhub.cloud/app/rest/builds/buildType:(id:Test_TestDataGen_AutoRelease)/statusIcon)
[![Quality Gate Status](https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.common%3Acommon-elhub-gradle&metric=alert_status)](https://sonar.elhub.cloud/dashboard?id=no.elhub.common%3Acommon-elhub-gradle)
[![Lines of Code](https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.common%3Acommon-elhub-gradle&metric=ncloc)](https://sonar.elhub.cloud/dashboard?id=no.elhub.common%3Acommon-elhub-gradle)
[![Vulnerabilities](https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.common%3Acommon-elhub-gradle&metric=vulnerabilities)](https://sonar.elhub.cloud/dashboard?id=no.elhub.common%3Acommon-elhub-gradle)
[![Bugs](https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.common%3Acommon-elhub-gradle&metric=bugs)](https://sonar.elhub.cloud/dashboard?id=no.elhub.common%3Acommon-elhub-gradle)
[![Code Smells](https://sonar.elhub.cloud/api/project_badges/measure?project=no.elhub.common%3Acommon-elhub-gradle&metric=code_smells)](https://sonar.elhub.cloud/dashboard?id=no.elhub.common%3Acommon-elhub-gradle)

## Table of Contents

* [About](#about)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
  * [Installation](#installation)
* [Usage](#usage)
* [Testing](#testing)
* [Issues](link-to-issues)
* [Contributing](link-to-contributing-file)
* [License](link-to-license-file)
* [Owners](link-to-codeowners-file)
* [Meta](#meta)


## About

This is the custom gradle distribution used by Elhub gradle projects. Having a custom gradle distribution allows us to 
wrap common logic that is used in most or all projects into a single package and reuse this in many projects. In practice,
it means that for Kotlin and Java projects the

## Getting Started

### Prerequisites

This project uses the standard gradle distribution.

### Installation

To build the custom distribution:

```sh
./gradlew assemble
```

To publish 
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

## Roadmap

See the
[open issues](https://jira.elhub.cloud/issues/?jql=project%20%3D%20TD%20AND%20component%20%3D%20common-elhub-gradle%20AND%20resolution%20%3D%20Unresolved)
for a list of proposed features (and known issues).

## Contributing

Contributing, issues and feature requests are welcome. See the
[Contributing](https://github.com/elhub.test-konfig/blob/main/CONTRIBUTING.md) file.

## Owners

This project is developed by [Elhub](https://github.com/elhub). For the specific development group responsible for this
code, see the [Codeowners](https://github.com/elhub/common-elhub-gradle/blob/main/CODEOWNERS) file.

## License

This project is [MIT](https://github.com/elhub/common-elhub-gradle/blob/main/LICENSE.md) licensed.
