# Repository Guidelines

## Project Structure & Module Organization

This repository is a single Android application module:

- `app/src/main/java/com/elicapo/yaesseptiembre/` contains Kotlin activities and the home-screen widget provider.
- `app/src/main/res/` contains layouts, drawables, launcher assets, strings, themes, and widget XML configuration.
- `app/src/test/` contains local JVM unit tests; `app/src/androidTest/` contains device/emulator tests.
- `app/build.gradle.kts`, `gradle/libs.versions.toml`, and the root Gradle files define Android, dependency, and toolchain configuration.

Keep new production code in the existing package and place resources in the appropriate typed `res` directory. Avoid committing generated build output or machine-specific `local.properties` changes.

## Build, Test, and Development Commands

Run commands from the repository root using the checked-in wrapper:

- `./gradlew assembleDebug` — build the debug APK.
- `./gradlew test` — run local JVM unit tests.
- `./gradlew connectedAndroidTest` — run instrumented tests on a connected device or emulator.
- `./gradlew installDebug` — build and install the debug app on a connected device.

Android Studio can also sync the project and launch the `app` configuration. The project targets SDK 37 and supports Android API 24+.

## Coding Style & Naming Conventions

Use Kotlin’s standard style with four-space indentation, clear expression-oriented code, and descriptive names. Classes use `PascalCase`; functions, properties, resource names, and IDs use `camelCase` or Android’s conventional `snake_case` resource naming. Keep package names lowercase. Use view binding where practical, and keep user-facing text in `res/values/strings.xml`. No separate formatter or linter configuration is currently checked in, so follow Android Studio’s Kotlin formatting.

## Testing Guidelines

Use JUnit 4 for local tests and AndroidX JUnit/Espresso for instrumented tests. Name test classes after the production component and test methods for the behavior they verify, for example `daysRemaining_beforeSeptember_returnsPositiveValue`. Add unit tests for date calculations and widget/UI tests when behavior depends on Android framework components.

## Commit & Pull Request Guidelines

Existing commits use short, sentence-case summaries (for example, `Logo restored`). Keep commits focused and describe the user-visible or technical change in the imperative where possible. Pull requests should include a concise summary, testing commands and results, any related issue, and screenshots for UI or widget changes. Call out device/API-level assumptions and resource or configuration changes explicitly.

## Security & Configuration Tips

Do not commit API keys, signing credentials, emulator data, or `local.properties`. Review manifest, widget, and resource changes for unintended exported components or externally accessible behavior before submitting a pull request.
