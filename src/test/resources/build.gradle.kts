group = "no.elhub.example"
description = "An example of a project used with elhub-gradle"

dependencies {
    implementation(platform("no.elhub.common:common-bom:0.1.0"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Kotlin Platform
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // Common Platform
    implementation("no.elhub.common:common-konfig")
    // Test Dependencies
    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-extensions-allure-jvm")
}
