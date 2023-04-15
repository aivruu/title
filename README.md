# title
![Build Status](https://img.shields.io/github/actions/workflow/status/aivruu/title/build.yml?branch=main)
[![GPL License](https://img.shields.io/badge/license-GPL-blue)](LICENSE)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/c6aa86d7571b459b99de59a86ecb5e6f)](https://app.codacy.com/gh/aivruu/title/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

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
