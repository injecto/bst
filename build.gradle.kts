plugins {
    java
}

group = "com.injecto"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile("com.google.code.findbugs:jsr305:3.0.2")
    testCompile("junit:junit:4.12")
    testCompile("org.mockito:mockito-core:3.2.0")
    testCompile("org.hamcrest:java-hamcrest:2.0.0.0")
}
