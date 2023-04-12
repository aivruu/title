plugins {
	id("com.github.johnrengelman.shadow") version("7.1.2")
	`java-library`
	`maven-publish`
}

val directory = property("group") as String
val release = property("version") as String

repositories {
	mavenLocal()
	maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
	mavenCentral()
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.19.4-R0.1-SNAPSHOT")
	
	compileAdapts("v1_8_R3", "v1_9_R2", "v1_10_R1", "v1_11_R1", "v1_12_R1")
	
	implementation("org.jetbrains:annotations:24.0.1")
}

fun compileAdapts(vararg adapts: String) {
	dependencies {
		for (adapt in adapts) {
			implementation(project(":adapt:$adapt"))
		}
	}
}

tasks {
	shadowJar {
		archiveClassifier.set("")
		archiveFileName.set("title-v$release.jar")
		
		destinationDirectory.set(file("$rootDir/bin/"))
	
		relocate("org.jetbrains.annotations", "$directory.libs.annotations")
	}
	
	clean {
		delete("$rootDir/bin/")
	}
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = directory
			artifactId = "base"
			version = release
			
			from(components["java"])
		}
	}
}