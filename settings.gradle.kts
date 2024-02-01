pluginManagement {
    repositories {
        maven("https://jitpack.io")
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    versionCatalogs {
        create("libs"){
            from(files("$rootDir/libs.toml"))
        }
    }
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        maven("https://jitpack.io")
        google()
        mavenCentral()
    }
}

rootProject.name = "WanAndroidCompose"
include(":app")
include(":core_base")
include(":core_network")
include(":feature_user")
include(":data_user")
include(":feature_article")
include(":data_article")
