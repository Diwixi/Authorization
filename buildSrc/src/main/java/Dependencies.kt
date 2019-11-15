object Application {
    val id = "com.diwixis.mangareader"
    val gradle = "1.3.50"
    val minSdk = 24
    val targetSdk = 28
}

object Module {
    val app = ":app"
    val auth = ":auth"
    val core = ":core"
}

private const val coroutinesVersion = "1.3.1"

object Coroutines {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
}

object Retrofit {
    val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.2.0"
    val retrofit = "com.squareup.retrofit2:retrofit:2.6.1"
    val coroutinesAdapter = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    val converter = "com.squareup.retrofit2:converter-jackson:2.1.0"
}

private const val koinVersion = "2.0.1"

object Koin {
    val core = "org.koin:koin-core:$koinVersion"
    val viewModel = "org.koin:koin-androidx-viewmodel:$koinVersion"
}

object Image {
    val coil = "io.coil-kt:coil:0.7.0"
}

private const val chuckerVersion = "3.0.1"

object Logger {
    val timber = "com.jakewharton.timber:timber:4.7.1"
    val chucker = "com.github.ChuckerTeam.Chucker:library:$chuckerVersion"
    val chuckerNoOp = "com.github.ChuckerTeam.Chucker:library-no-op:$chuckerVersion"
}

private const val jacksonVersion = "2.10.0.pr3"

object Json {
    val jacksonDatabind = "com.fasterxml.jackson.core:jackson-databind:$jacksonVersion"
    val jacksonKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion"
}

private const val lifecycleVersion = "2.2.0-alpha04"
private const val lifecycleOldVersion = "2.1.0"

object Lifecycle {
    val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
    val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"
    val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
    val extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
    val runtime = "androidx.lifecycle:lifecycle-runtime:$lifecycleVersion"
    val common = "androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion"
    val livecycleCompiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
}

object Room {
    val core = "androidx.room:room-runtime:2.1.0"
    val compiler = "androidx.room:room-compiler:2.1.0"
}

object AndroidX {
    val material = "com.google.android.material:material:1.0.0"
    val annotation = "androidx.annotation:annotation:1.1.0"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:1.1.3"
}

object Android {
    val multidex = "com.android.support:multidex:1.0.3"
    val jdk = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.50"
    val junit = "junit:junit:4.12"
}