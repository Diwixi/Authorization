import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import java.util.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    val versionMajor = property("version_major").toString().toInt()
    val versionMinor = property("version_minor").toString().toInt()
    val versionPatch = property("version_patch").toString().toInt()
    val versionBuild = property("version_build").toString().toInt()

    compileSdkVersion(Application.targetSdk)

    defaultConfig {
        applicationId = Application.id
        minSdkVersion(Application.minSdk)
        targetSdkVersion(Application.targetSdk)
        multiDexEnabled = true

        versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
        versionName = "${versionMajor}.${versionMinor}.${versionPatch}"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    fun getPropertyByName(propertyName: String): String {
        val local = Properties()
        val localProperties: File = rootProject.file("local.properties")
        if (!localProperties.exists()) return ""
        localProperties.inputStream().use { local.load(it) }
        return local.getProperty(propertyName)
    }

    buildTypes {
        val userLogin = getPropertyByName("project.userLogin")
        val userPassword = getPropertyByName("project.userPassword")
        val clientId = getPropertyByName("project.clientId")
        val clientSecret = getPropertyByName("project.clientSecret")
        val apiBaseUrl = "https://kitsu.io/api/"

        getByName("debug") {
            buildConfigField("String", "API_BASE_URL", "\"$apiBaseUrl\"")
            buildConfigField("String", "DEV_USER_LOGIN", "\"$userLogin\"")
            buildConfigField("String", "DEV_USER_PASSWORD", "\"$userPassword\"")
            buildConfigField("String", "CLIENT_ID", "\"$clientId\"")
            buildConfigField("String", "CLIENT_SECRET", "\"$clientSecret\"")

            isDebuggable = true //дебаг
            isMinifyEnabled = false //удаление неиспользуемого кода
            isShrinkResources = false //удаление неиспользованных ресурсов
        }
        getByName("release") {
            buildConfigField("String", "API_BASE_URL", "\"$apiBaseUrl\"")
            buildConfigField("String", "DEV_USER_LOGIN", "\" \"")
            buildConfigField("String", "DEV_USER_PASSWORD", "\" \"")
            buildConfigField("String", "CLIENT_ID", "\"$clientId\"")
            buildConfigField("String", "CLIENT_SECRET", "\"$clientSecret\"")

            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            setProguardFiles(
                listOf(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            )
        }
    }

    dexOptions {
        preDexLibraries = true
        maxProcessCount = 4
        javaMaxHeapSize = "8g"
        jumboMode = true
    }

    lintOptions {
        isAbortOnError = true
        isCheckReleaseBuilds = true
        isNoLines = true
        xmlReport = false
    }

    androidExtensions { isExperimental = true }

    //TODO Починить. Не работает Т_Т
    applicationVariants.all { variant ->
        val flavor = variant.productFlavors[0]
        val buildType = variant.buildType

        val outDir = file("${rootDir}/out/${buildType.name}")
        val mappingOutDir = file("${outDir}/mapping")
        if (!outDir.exists()) {
            outDir.mkdirs()
        }
        if (!mappingOutDir.exists()) {
            mappingOutDir.mkdirs()
        }

        println("variant: $variant")
        variant.assembleProvider?.get()?.doLast {
            variant.outputs.all { output ->
                val outputImpl = output as BaseVariantOutputImpl
                val fileName = output.outputFileName
                    .replace(
                        "-release",
                        "-release-MangaReader-v${variant.versionName}.apk"
                    )
                    .replace(
                        "-debug",
                        "-debug-MangaReader-v${variant.versionName}.apk"
                    )
                println("output file name: $fileName")
                outputImpl.outputFileName = fileName
                true
            }
        }
        true
    }
}

dependencies {
    //COROUTINES
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    // RETROFIT
    implementation(Retrofit.loggingInterceptor)
    implementation(Retrofit.retrofit)
    implementation(Retrofit.coroutinesAdapter)
    implementation(Retrofit.converter)

    // KOIN
    implementation(Koin.core)
    implementation(Koin.viewModel)

    // IMAGE
    implementation(Image.coil)

    // LOGGER
    implementation(Logger.timber)
    debugImplementation(Logger.chucker)
    releaseImplementation(Logger.chuckerNoOp)

    //JSON
    api(Json.jacksonDatabind)
    implementation(Json.jacksonKotlin)

    //LIFECYCLE
    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.runtimeKtx)
    implementation(Lifecycle.livedata)
    implementation(Lifecycle.extensions)
    implementation(Lifecycle.runtime)
    implementation(Lifecycle.common)
    kapt(Lifecycle.livecycleCompiler)

    //ANDROIDX
    implementation(AndroidX.material)
    implementation(AndroidX.annotation)
    implementation(AndroidX.constraintLayout)
    implementation(Room.core)
    kapt(Room.compiler)

    implementation(Android.jdk)
    implementation(Android.multidex)
    testImplementation(Android.junit)
}

kapt { generateStubs = true }