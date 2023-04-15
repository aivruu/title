plugins {
	`java-library`
	`maven-publish`
}

val directory = property("group") as String
val release = property("version") as String

repositories {
	mavenLocal()
	maven("https://repo.codemc.org/repository/nms/")
	mavenCentral()
}

dependencies {
	compileOnly("org.spigotmc:spigot:1.10.2-R0.1-SNAPSHOT")
	
	implementation(project(":base"))
	implementation("org.jetbrains:annotations:24.0.1")
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			groupId = directory
			artifactId = "adapt-v1_10_R1"
			version = release
			
			from(components["java"])
		}
	}
}