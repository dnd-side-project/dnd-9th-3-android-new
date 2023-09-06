pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
        maven {
            setUrl("https://jitpack.io")
        }
        maven {
            setUrl("https://devrepo.kakao.com/nexus/content/groups/public/")
        }
    }
}
rootProject.name = "Gooding"
include(":app")
include(":core")
include(":feature")
include(":app:login")
include(":app:common")
include(":feature:feed")
include(":feature:search")
include(":feature:record")
include(":feature:myaccount")
include(":core:api")
include(":core:data")
include(":core:model")
