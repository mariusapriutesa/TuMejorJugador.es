# TuMejorJugador.es
Proyecto TFG, aplicacion que muestra noticias deportivas.

Implementación y la lógica :
En este apartado, analizaremos la implementación necesaria del sistema correspondiente al desarrollo y la programación software del proyecto TuMejorJugador.es. Dividiremos en tres partes: aspectos relacionados con API NEWS, con  Android Studio y también con Firebase.
API NEWS 
En la pagina https://newsapi.org/docs/get-started  pulsamos :


para obtener la llave de tu API (te va a pedir que te registres)  :




El siguiente paso es hacer el Request (el get):

Como se ve en la imagen , nosotros cogemso https://newsapi.org/v2/ y le estamos añadiendo las características que queremos  , con las opciones que nos los dan en la documentación(https://newsapi.org/docs/endpoints/everything)  , y la final le añadimos nuestra llave (el api Key).

Android Studio :



Estructura de la aplicación en android studio.

Esta es la estructura de la aplicación en android studio .
En la carpeta app/java se encuentra todos los archivos  de código fuente Java, conformando la parte lógica de nuestro proyecto. 

La carpeta Interfaces , contiene las constantes estáticas y métodos abstractos , que son necesarias para la realización del API NEWS.

La carpeta APIModel, contiene las clases tipo Adapter y Objetos necesarias para la implementación del API y su lista de noticias.

La carpeta APIClases agrupa las activities dedicadas a la subida de las noticias de latinoamérica en la plataforma.

En la otra imagen ,tenemos la carpeta app/res la cual contiene todos los recursos sin código, como los diseños XML, imágenes, strings e iconos. Estos recursos son almacenados en diferentes subcarpetas dependiendo del tipo de recurso:
Values: en esta carpeta se encuentran los diferentes ficheros XML que indican diferentes valores usados en la aplicación
Mipmap: en esta carpeta tenemos el icono de lanzamiento de nuestra aplicación.
Menu: en esta carpeta se encuentra el archivo XML con los buttones que van a aparecer en el tool bar.
 Layout: en esta carpeta se almacenan los ficheros XML que equivalen a las pantallas de nuestra aplicación.
Drawable: esta carpeta almacena aquellos recursos que son normalmente de tipo imagen
AndroidManifest.xml : es un archivo de configuración donde aplicamos las configuraciones mínimas de nuestra aplicación. 
En él se definen:
 - El nombre de la aplicación, los activitis, los estilos de la aplicación, etc.
 - También la Versión mínima del API de Android necesaria para poder ser ejecutada, la versión de la aplicación, etc.
 - Permisos que necesita la aplicación para su correcto funcionamiento, como son:
<intent-filter>
   <action android:name="android.intent.action.MAIN" />

   <category android:name="android.intent.category.LAUNCHER" />
</intent-filter>



En la carpeta Grandle Scripts, se almacenan los ficheros Grandle, los cuales permiten compilar y construir la aplicación.



*En nuestro caso en android build.gradle(module) hay que añadir las siguientes dependencias :
dependencies {
implementation 'com.squareup.retrofit2:retrofit:2.9.0' 
implementation 'com.squareup.retrofit2:converter-gson:2.9.0' 
implementation 'androidx.appcompat:appcompat:1.4.1' 
implementation 'com.google.android.material:material:1.5.0' 
implementation 'androidx.constraintlayout:constraintlayout:2.1.3' 
implementation 'com.google.firebase:firebase-auth:21.0.1' 
implementation 'com.google.firebase:firebase-firestore:24.0.1' 
implementation 'com.google.firebase:firebase-database:20.0.3' implementation('com.squareup.picasso:picasso:2.71828') 
implementation platform('com.google.firebase:firebase-bom:29.1.0') test
Implementation 'junit:junit:4.13.2' androidTest
Implementation 'androidx.test.ext:junit:1.1.3' androidTest
Implementation 'androidx.test.espresso:espresso-core:3.4.0' }


3.Firebase:

Como hemos comentado anteriormente los productos usados en este aplicación han sido: Authentication y Realtime Database



Authentication: En authentication se almacenan las credenciales de los usuarios de la aplicación registrados mediante el correo y la contraseña.

Realtime Database: En este apartado vamos a ver nuestra base de datos NoSQL en tiempo real, la cual está organizada en forma de árbol JSON.
Nuestra base de datos se estructura en dos grupos:
Jugadores y Usuarios.
  Usuarios: es donde almacenamos la información de cada uno de los  usuario. Cada usuario se identifica por su ID  que es el correo. 

Jugadores: es donde se almacena cada noticia , usado como id el título de la noticia.
