plugins {
	id("java")
	id("net.minecrell.plugin-yml.bukkit") version("0.5.3")
}

repositories {
	mavenLocal()
	maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
	mavenCentral()
}

dependencies {
	compileOnly("org.spigotmc:spigot-api:1.8.8-R0.1-SNAPSHOT")
	
	implementation(project(":base"))
	implementation(project(":adapt:v1_8_R3"))
}

bukkit {
	val directory = property("group") as String
	val release = property("version") as String

	name = "PluginTest"
	main = "$directory.PluginTest"
	authors = listOf("InitSync")

	apiVersion = "1.13"
	version = release

	commands {
		register("test") {
			description = "Test command."
		}
	}
}