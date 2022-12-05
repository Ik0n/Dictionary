import org.gradle.api.JavaVersion

object Config {
    const val application_id = "ru.geekbrains.dictionary"
    const val compile_sdk = 33
    const val min_sdk = 28
    const val target_sdk = 33
    val java_version = JavaVersion.VERSION_1_8
}

object Releases {
    const val version_code = 1
    const val version_name = "1.0"
}

object Modules {
    const val app = ":app"
    const val core = ":core"
    const val model = ":model"
    const val repository = ":repository"
    const val utils = ":utils"
    const val historyScreen = ":historyscreen"
}

object Versions {
    const val kotlinStdlib = "1.7.20"
    const val coreKtx = "1.9.0"
    const val appcompat = "1.5.1"
    const val material = "1.6.1"
    const val constraintLayout = "2.1.4"
    const val swipeRefreshLayout = "1.1.0"

    // Test
    const val junit = "4.13.2"
    const val androidxTestExtJunit = "1.1.3"
    const val espressoCore = "3.4.0"

    // ViewModel
    const val lifecycleViewModel = "2.5.1"
    const val lifecycleViewModelKtx = "2.5.1"

    // Rx-java
    const val rxAndroid = "2.1.0"
    const val rxJava = "2.2.8"

    // Retrofit 2
    const val retrofit = "2.9.0"
    const val converterGson = "2.9.0"
    const val loggingInterceptor = "3.12.1"

    // Dagger 2
    const val daggerAndroid = "2.44.1"
    const val daggerAndroidSupport = "2.44.1"
    const val daggerCompiler = "2.44.1"
    const val daggerAndroidProcessor = "2.44.1"

    // Coroutines
    const val kotlinxCoroutinesCore = "1.5.1"
    const val kotlinxCoroutinesAndroid = "1.5.0"
    const val retrofit2KotlinCoroutinesAdapter = "0.9.2"

    // Koin
    const val koinCore = "3.1.2"
    const val koinAndroid = "3.1.2"
    const val koinAndroidCompat = "3.1.2"
    const val koinViewModel = "3.1.2"
    const val koinAndroidScope = "3.1.2"

    //Picasso
    const val picasso = "2.71828"

    //Glide
    const val glide = "4.11.0"
    const val compiler = "4.11.0"

    //Coil
    const val coil = "0.11.0"

    //Room
    const val roomRuntime = "2.3.0"
    const val roomCompiler = "2.3.0"
    const val roomKtx = "2.3.0"

}

object Dagger {
    const val dagger_android = "com.google.dagger:dagger-android:${Versions.daggerAndroid}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.daggerAndroidSupport}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.daggerCompiler}"
    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.daggerAndroidProcessor}"
}

object Rx {
    const val rx_android = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    const val rx_java = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
}

object Test {
    const val junit = "junit:junit:${Versions.junit}"
    const val androidx_test_ext_junit = "androidx.test.ext:junit:${Versions.androidxTestExtJunit}"
    const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}

object Design {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val swipe_refresh_layout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
}

object Kotlin {
    const val core = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val stdlib =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinStdlib}"
    const val coroutines_core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinxCoroutinesCore}"
    const val coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutinesAndroid}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycleViewModel}"
    const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtx}"
}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converter_gson =
        "com.squareup.retrofit2:converter-gson:${Versions.converterGson}"
    const val adapter_coroutines =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofit2KotlinCoroutinesAdapter}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
}

object Koin {
    const val koin_android = "io.insert-koin:koin-android:${Versions.koinAndroid}"
    const val koin_core = "io.insert-koin:koin-core:${Versions.koinCore}"
    const val koin_android_compat = "io.insert-koin:koin-android-compat:${Versions.koinAndroidCompat}"
    const val koin_android_scope = "io.insert-koin:koin-android-scope:${Versions.koinAndroidScope}"
    const val koin_view_model = "io.insert-koin:koin-android-viewmodel:${Versions.koinViewModel}"
}

object Coil {
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}

object Picasso {
    const val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
}

object Glide {
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${Versions.compiler}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${Versions.roomRuntime}"
    const val compiler = "androidx.room:room-compiler:${Versions.roomCompiler}"
    const val room_ktx = "androidx.room:room-ktx:${Versions.roomKtx}"
}





