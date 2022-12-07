plugins {
    kotlin("jvm") version "1.7.22"
    id("org.jetbrains.intellij") version "1.10.0"
    id("io.github.turansky.kfc.version") version "5.71.0"
}

repositories {
    mavenCentral()
}

intellij {
    pluginName.set("yfiles")

    type.set("IU")
    version.set("2022.3")

    plugins.set(
        listOf(
            "java",
            "org.jetbrains.kotlin",
            "JavaScript"
        )
    )
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }

    runIde {
        jvmArgs(
            "-Xms1g",
            "-Xmx4g"
        )
    }

    patchPluginXml {
        sinceBuild.set("222.3345")
        untilBuild.set("224.*")
    }

    publishPlugin {
        token.set(project.property("intellij.publish.token") as String)
    }

    wrapper {
        gradleVersion = "7.5.1"
    }
}
