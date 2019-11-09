import org.jetbrains.kotlin.config.AnalysisFlags.experimental
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
//    val versionMajor = Integer.parseInt(property("version_major"))
//    val versionMinor = Integer.parseInt(version_minor)
//    val versionPatch = Integer.parseInt(version_patch)
//    val versionBuild = Integer.parseInt(version_build)

    compileSdkVersion(28)

    defaultConfig {
        applicationId = "com.diwixis.mangareader"
        minSdkVersion(24)
        targetSdkVersion(28)
        multiDexEnabled = true

//        versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
//        versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
//            val userLogin = getStringFromProperty("local.properties", "project.userLogin") ?: ""
//            val userPassword =
//                getStringFromProperty("local.properties", "project.userPassword") ?: ""
//            val clientId = getStringFromProperty("local.properties", "project.clientId") ?: ""
//            val clientSecret =
//                getStringFromProperty("local.properties", "project.clientSecret") ?: ""

//            isDebugable = true //дебаг
//            isMinifyEnabled = false //удаление неиспользуемого кода
//            isShrinkResources = false //удаление неиспользованных ресурсов
//            buildConfigField "String", "API_BASE_URL", '"https://kitsu.io/api/"'
//            buildConfigField "String", "DEV_USER_LOGIN", "\"$userLogin\""
//            buildConfigField "String", "DEV_USER_PASSWORD", "\"$userPassword\""
//            buildConfigField "String", "CLIENT_ID", "\"$clientId\""
//            buildConfigField "String", "CLIENT_SECRET", "\"$clientSecret\""
        }
        getByName("release"){
//            def clientId = getStringFromProperty ("local.properties", "project.clientId") ?: ""
//            def clientSecret = getStringFromProperty ("local.properties", "project.clientSecret") ?: ""

//            isDebugable = true
//            isMinifyEnabled = false
//            isShrinkResources = false
//            proguardFiles getDefaultProguardFile ('proguard-android-optimize.txt'), 'proguard-rules.pro'
//            buildConfigField "String", "API_BASE_URL", '"https://kitsu.io/api/"'
//            buildConfigField "String", "DEV_USER_LOGIN", '"'
//            buildConfigField "String", "DEV_USER_PASSWORD", '""'
//            buildConfigField "String", "CLIENT_ID", "\"$clientId\""
//            buildConfigField "String", "CLIENT_SECRET", "\"$clientSecret\""
        }
    }

//    dexOptions {
//        isPreDexLibraries = true
//        maxProcessCount(4)
//        javaMaxHeapSize = "8g"
//        isJumboMode = true
//    }
//
//    lintOptions {
//        isAbortOnError = true
//        isCheckReleaseBuilds = true
//        isNoLines = true
//        isXmlReport = false
//    }

//    androidExtensions { experimental = true }

//    applicationVariants.all { variant ->
//        def flavor = variant . productFlavors [0]
//        def buildType = variant . buildType
//
//                File outDir = file ("${rootDir}/out/${buildType.name}")
//        File mappingOutDir = file ("${outDir}/mapping")
//        if (!outDir.exists()) {
//            outDir.mkdirs()
//        }
//        if (!mappingOutDir.exists()) {
//            mappingOutDir.mkdirs()
//        }
//        variant.assemble.doLast {
//            variant.outputs.each { output ->
//                copy {
//                    from output . outputFile
//                            into outDir
//                            rename { fileName -> "MangaReader-v${variant.versionName}.apk" }
//                }
//            }
//        }
//    }
}

dependencies {
//    implementation fileTree (org.gradle.internal.impldep.bsh.commands.dir: 'libs', include: ['*.jar'])
    implementation("com.android.support:multidex:1.0.3")

    //COROUTINES
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.1")

    // RETROFIT
    implementation("com.squareup.okhttp3:logging-interceptor:4.2.0")
    implementation("com.squareup.retrofit2:retrofit:2.6.1")
    implementation("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // KOIN
    implementation("org.koin:koin-core:2.0.1")
    implementation("org.koin:koin-androidx-viewmodel:2.0.1")

    // IMAGE
    implementation("io.coil-kt:coil:0.7.0")

    // LOGGER
    implementation("com.jakewharton.timber:timber:4.7.1")
    debugImplementation("com.github.ChuckerTeam.Chucker:library:3.0.1")
    releaseImplementation("com.github.ChuckerTeam.Chucker:library-no-op:3.0.1")

    //JSON
    api("com.fasterxml.jackson.core:jackson-databind:2.10.0.pr3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.0.pr3")

    //LIFECYCLE
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0-alpha04")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.2.0-alpha04")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0-alpha04")

    //ANDROIDX
    implementation("com.google.android.material:material:1.0.0")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("androidx.constraintlayout:constraintlayout:1.1.3")
    implementation("androidx.lifecycle:lifecycle-extensions:2.1.0")
    implementation("androidx.lifecycle:lifecycle-runtime:2.1.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.1.0")
    implementation("androidx.room:room-runtime:2.1.0")
    kapt("androidx.lifecycle:lifecycle-compiler:2.1.0")
    kapt("androidx.room:room-compiler:2.1.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.50")
    testImplementation("junit:junit:4.12")
}

//kapt { generateStubs = true }