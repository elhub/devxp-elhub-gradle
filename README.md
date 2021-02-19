# Project Name

<!-- PROJECT SHIELDS -->
<!--
*** Add Project Shields here. Several of Elhubs systems provide shields, so why not use them to give info at a glance.
*** [TeamCity Builds][SonarQube Quality Gate][SonarQube Vulnerabilities][SonarQube bugs][SonarQube smells][SonarQube Coverage]
-->

<!-- TABLE OF CONTENTS -->
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


<!-- ABOUT THE PROJECT -->
## About

This is the custom gradle distribution used by Elhub gradle projects. Having a custom gradle distribution allows us to 
wrap common logic that is used in most or all projects into a single package and reuse this in all projeects.

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

* None 

### Installation

To build the custom distribution:

```sh
./gradlew buildDist
```

To publish 
```sh
./gradlew artifactoryPublish
```

<!-- USAGE EXAMPLES -->
## Usage

To make use of elhub-gradle, edit your gradle/wrapper/gradle-wrapper.properties file and change your distributionUrl
to:
```
distributionUrl=https\://<repository-server>/elhub-bin/elhub-gradle/elhub-gradle-<version>.zip
```

Where repository-server is the artifact server being used and version is the latest version of elhub-gradle. You
must have access to the artifact server in question, obviously.


<!-- TESTING -->
## Testing

N/A

<!-- ROADMAP -->
## Roadmap

See the
[open issues](https://jira.elhub.cloud/browse/TD-1?jql=project%20%3D%20TD%20AND%20component%20IN%20(%22Dev%20Tools%22))
for a list of proposed features and known issues (requires access to Elhub Jira).


<!-- META -->
## Meta

N/A