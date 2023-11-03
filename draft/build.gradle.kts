import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    java
    id("org.springframework.boot") version "2.7.17"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    id("org.openapi.generator") version "5.3.1"
    kotlin("jvm") version "1.9.20-RC2"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

sourceSets {
    main {
        java {
            srcDir("$buildDir/generated/")
        }
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.openapitools:jackson-databind-nullable:0.2.6")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.swagger:swagger-annotations:1.6.2")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.1.2")
    implementation("javax.validation:validation-api:2.0.1.Final")
//    implementation("javax.validation:com.springsource.javax.validation:1.0.0.GA")
//    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    implementation(kotlin("stdlib-jdk8"))
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register("petsOpenApiGenerate", GenerateTask::class) {
    generatorName.set("spring")
//	library.set("spring-boot")
    inputSpec.set("$rootDir/openapi/petstore.yaml")
    outputDir.set("$buildDir/generated/petstore")
    apiPackage.set("org.openapi.pets")
    invokerPackage.set("org.openapi.invoker")
    modelPackage.set("org.openapi.pets.model")
    generateApiDocumentation = false
    generateModelTests = false
    generateApiTests = false
    configOptions.set(
            mapOf(
                    "dateLibrary" to "java8",
                    "skipDefaultInterface" to "true",
                    "interfaceOnly" to "true",
                    "serializableModel" to "true",
                    "interfaceOnly" to "true",
                    "useTags" to "false",
                    "hideGenerationTimeStamp" to "true",
                    "openApiNullable" to "false",
                    "sourceFolder" to ""
            )
    )
    globalProperties.putAll(mapOf("generateSupportingFiles" to "false"))
}

tasks.register("greetingsOpenApiGenerate", GenerateTask::class) {
    generatorName.set("spring")
    library.set("spring-boot")
    inputSpec.set("$rootDir/openapi/greetings.yaml")
    outputDir.set("$buildDir/generated/greetings")
    apiPackage.set("org.openapi.greeting")
    invokerPackage.set("org.openapi.invoker")
    modelPackage.set("org.openapi.greeting.model")
    generateApiDocumentation = false
    generateModelTests = false
    generateApiTests = false
    removeOperationIdPrefix = true
    configOptions.set(
            mapOf(
                    "dateLibrary" to "java8",
                    "skipDefaultInterface" to "true",
                    "interfaceOnly" to "true",
                    "serializableModel" to "true",
                    "useTags" to "false",
                    "hideGenerationTimeStamp" to "true",
                    "openApiNullable" to "false",
                    "sourceFolder" to "",
            )
    )
    globalProperties.putAll(mapOf("generateSupportingFiles" to "false"))
    //
    // "apis" to "", don't generate models
}

openApiGenerate {

}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}