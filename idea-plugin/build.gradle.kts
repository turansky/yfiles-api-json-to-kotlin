group = "com.github.turansky.yfiles"
version = "0.1.4-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.71"
    id("org.jetbrains.intellij") version "0.4.18"
    id("com.github.autostyle") version "3.0"
}

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
}

intellij {
    pluginName = "yfiles"

    version = "2019.3.3"

    setPlugins(
        "gradle",
        "java",
        "org.jetbrains.kotlin:1.3.71-release-IJ2019.3-1"
    )
}

autostyle {
    kotlin {
        endWithNewline()
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
            allWarningsAsErrors = true
        }
    }

    patchPluginXml {
        sinceBuild("193.5233")
        untilBuild("201.*")
    }

    publishPlugin {
        setToken(project.property("intellij.publish.token"))
    }

    wrapper {
        gradleVersion = "6.3"
        distributionType = Wrapper.DistributionType.ALL
    }
}
