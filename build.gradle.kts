import java.util.*

buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.2")
        classpath(
            kotlin(
                module = "gradle-plugin",
                version = Application.gradle
            )
        )//добавить версию в отдельном файле
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}



task<Delete>(name = "clean") {
    delete(rootProject.buildDir)
}

tasks.register("compute") {
    val local = Properties()
    val localProperties: File = rootProject.file("local.properties")
    if (localProperties.exists()) {
        localProperties.inputStream().use { local.load(it) }
    }
    doLast { println("The userLogin = ${local.getProperty("project.userLogin")}") }
//    doLast { println("The userLogin = $userLogin") }
//    doLast { println("The userPassword = $userPassword") }
//    doLast { println("The clientId = $clientId") }
//    doLast { println("The clientSecret = $clientSecret") }
}