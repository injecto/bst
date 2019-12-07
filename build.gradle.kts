plugins {
    java
}

group = "com.injecto"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile("com.google.code.findbugs", "jsr305", "3.0.2")
    testCompile("junit", "junit", "4.12")
}

//configure<JavaPluginConvention> {
//    sourceCompatibility = JavaVersion.VERSION_11
//}