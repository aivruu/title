# title
![GitHub release (latest by date)](https://img.shields.io/github/v/release/aivruu/title)
[![GPL License](https://img.shields.io/badge/license-GPL-blue)](LICENSE)

`title` is a packet-based simple library for Bukkit platforms to show titles, actionbars and more.

# Building
`./gradlew shadowJar`

# Import
```gradle
implementation("com.github.aivruu.title:base:RELEASE")
implementation("com.github.aivruu.title:ADAPT:RELEASE")
// Check the available adapts in 'adapt/'.

shadowJar {
  relocate("com.aivruu.title", "com.yourPackage.libs.title")
}
```
