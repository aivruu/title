plugins {
	`java-library`
	`maven-publish`
	id("com.github.johnrengelman.shadow") version("7.1.2")
}

val directory = property("group") as String
val release = property("version") as String

repositories {
	mavenLocal()
	maven("https://repo.codemc.org/repository/nms/")
	mavenCentral()
}

dependencies {
	compileOnly("org.spigotmc:spigot:1.8.8-R0.1-SNAPSHOT")
	
	implementation(project(":base"))
	implementation("org.jetbrains:annotations:24.0.1")
}

tasks {
	shadowJar {
		archiveClassifier.set("")
		archiveFileName.set("title-v1_8_R3-v$release.jar")
		
		destinationDirectory.set(file("$rootDir/bin/"))
		
		relocate("org.jetbrains.annotations", "$directory.v1_8_R3.libs.annotations")
	}
	
	clean {
		delete("$rootDir/bin/")
	}
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = directory
			artifactId = "adapt-v1_8_R3"
			version = release
			
			from(components["java"])
		}
	}
}