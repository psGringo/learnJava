plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

//val jar: Jar by tasks
//val bootJar: BootJar by tasks
//
//bootJar.enabled = false
//jar.enabled = true
//
//
//tasks.getByName<BootJar>("bootJar") {
//    enabled = false
//}
//
//tasks.getByName<Jar>("jar") {
//    enabled = true
//}