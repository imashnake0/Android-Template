@file:Suppress("UnstableApiUsage")

import org.gradle.api.internal.FeaturePreviews


pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Template"
include(":app")

plugins {
    id("com.android.settings") version("9.2.1")
}

android {
    buildToolsVersion = "37.0.0"
    compileSdk = 37
    targetSdk = 37
    minSdk = 29
}

enableFeaturePreview(FeaturePreviews.Feature.TYPESAFE_PROJECT_ACCESSORS.name)
