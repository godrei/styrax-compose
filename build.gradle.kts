import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Libs.androidGradlePlugin)
        classpath(Libs.androidJUnit5GradlePlugin)
        classpath(Libs.kotlinGradlePlugin)
    }
}

plugins {
    id(Libs.gradleVersionsGradlePlugin) version gradleVersionsVersion
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

val nonStable = mapOf(
    "androidx.appcompat" to listOf("alpha"),
    "androidx.core" to listOf("alpha"),
    "com.google.android.material" to listOf("alpha"),
    "org.junit.jupiter" to listOf("M1"),
    "org.jetbrains.kotlin" to listOf("RC", "M1", "M2")
)

fun isNonStable(group: String, module: String, version: String) =
    nonStable[group]?.any { version.contains(it) } ?: false

tasks.named<DependencyUpdatesTask>("dependencyUpdates").configure {
    gradleReleaseChannel = "current"
    rejectVersionIf {
        isNonStable(candidate.group, candidate.module, candidate.version)
    }
}