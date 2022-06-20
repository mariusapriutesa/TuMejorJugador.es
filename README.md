# TuMejorJugador.es
Proyecto TFG, aplicacion que muestra noticias deportivas.


 - Permisos que necesita la aplicación para su correcto funcionamiento, como son:
 - 
//<intent-filter>
  // <action android:name="android.intent.action.MAIN" />

  // <category android:name="android.intent.category.LAUNCHER" />
//</intent-filter>


En la carpeta Grandle Scripts, se almacenan los ficheros Grandle, los cuales permiten compilar y construir la aplicación.


*En nuestro caso en android build.gradle(module) hay que añadir las siguientes dependencias :
plugins {
    id 'com.android.application'
    id 'com.google.gms.google-services'
}

android {
    compileSdk 31

    defaultConfig {
        applicationId "com.example.tumejorjugadores"
        minSdk 23
        targetSdk 31
        versionCode 1
        versionName "1.0"
        multiDexEnabled true

        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    namespace 'com.example.tumejorjugadores'
}


dependencies {
    implementation 'com.android.support:multidex:1.0.1'

    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'
    implementation 'androidx.appcompat:appcompat:1.4.1'
    implementation 'com.google.android.material:material:1.5.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.3'
    implementation 'com.google.firebase:firebase-auth:21.0.1'
    implementation 'com.google.firebase:firebase-firestore:24.0.1'
    implementation 'com.google.firebase:firebase-database:20.0.3'
    implementation('com.squareup.picasso:picasso:2.71828')
    implementation platform('com.google.firebase:firebase-bom:29.1.0')
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
}



