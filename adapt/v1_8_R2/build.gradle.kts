plugins {
	id("com.github.johnrengelman.shadow") version("7.1.2")
	`java-library`
	`maven-publish`
}

val release = property("version") as String

repositories {
	maven("https://repo.codemc.org/repository/nms/")
	mavenCentral()
}

dependencies {
	compileOnly("org.spigotmc:spigot:1.8.3-R0.1-SNAPSHOT")
	
	implementation(project(":api"))
}

tasks {
	shadowJar {
		archiveFileName.set("XTitle-adapt-v1_8_R2-$release.jar")
		destinationDirectory.set(file("$rootDir/bin/"))
	}
	
	clean {
		delete("$rootDir/bin/")
	}
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = "net.xtitle.adapt"
			artifactId = "adapt-v1_8_R2"
			version = release
			
			from(components["java"])
		}
	}
}