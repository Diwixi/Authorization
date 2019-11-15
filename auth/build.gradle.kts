plugins {
    id("com.android.application")

    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Application.targetSdk)

    defaultConfig {
        applicationId = Application.id
        minSdkVersion(Application.minSdk)
        targetSdkVersion(Application.targetSdk)
        multiDexEnabled = true
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
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
