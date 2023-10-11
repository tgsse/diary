pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            // TODO remove once the 2 external libraries are not needed
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "Diary"
include(":app")
 