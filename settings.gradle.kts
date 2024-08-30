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

rootProject.name = "test"
include(":app")
include(":servicenormal")
include(":foregroundservice")
include(":workmanager")
include(":linearlayout")
include(":relativelayout")
include(":constraintlayout")
include(":framelayout")
include(":fragmenttest")
include(":activitytest")
include(":maxfragmenttest")
