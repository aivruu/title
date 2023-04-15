# title
![GitHub release (latest by date)](https://img.shields.io/github/v/release/aivruu/title)
![Build Status](https://img.shields.io/github/actions/workflow/status/aivruu/title/build.yml?branch=main)
[![GPL License](https://img.shields.io/badge/license-GPL-blue)](LICENSE)

`title` is a packet-based simple library for Bukkit to show titles, actionbars and more.

# Building
`./gradle shadowJar`

# Import
```gradle
implementation("com.github.aivruu.title:base:1.0.5")

shadowJar {
  relocate("com.aivruu.title", "com.yourPackage.libs.title")
}
```
