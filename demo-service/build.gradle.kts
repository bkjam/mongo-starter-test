import org.springframework.boot.gradle.tasks.run.BootRun
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	kotlin("jvm")
	kotlin("plugin.spring")
}

java.sourceCompatibility = JavaVersion.VERSION_11

dependencies {
	// Database Starter
	implementation(project(":demo-mongodb-starter"))

	// Web
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Kotlin
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
	withType<BootRun> {
		args("--spring.profiles.active=development")
	}
	withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}
	withType<Test> {
		useJUnitPlatform()
		testLogging {
			events("passed", "skipped", "failed")
		}
	}
}
