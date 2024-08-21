import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.9.23"
    val springBootVersion = "3.3.2"
    id("java")
    id("net.thebugmc.gradle.sonatype-central-portal-publisher") version "1.1.1"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.kapt") version kotlinVersion
}

group = "io.github.breninsul"
version = "1.0.1"

val javaVersion = JavaVersion.VERSION_21

java {
    sourceCompatibility = javaVersion
}

java {
    withJavadocJar()
    withSourcesJar()
}
repositories {
    mavenCentral()
}
tasks.compileJava {
    dependsOn.add(tasks.processResources)
}
tasks.compileKotlin {
    dependsOn.add(tasks.processResources)
}

dependencies {
    api("io.github.breninsul:WebPDecoderJN:1.3-b")
    api("io.github.breninsul:named-limited-virtual-thread-executor:1.0.2")
    api("org.apache.pdfbox:pdfbox:3.0.2")
    api("org.apache.tika:tika-core:2.9.2")
    api("com.sksamuel.scrimage:scrimage-core:4.2.0")
    api("com.sksamuel.scrimage:scrimage-format-png:4.2.0")
    api("com.sksamuel.scrimage:scrimage-formats-extra:4.2.0")
    api("com.sksamuel.scrimage:scrimage-hash:4.2.0")
    api("com.sksamuel.scrimage:scrimage-filters:4.2.0")
    api("com.sksamuel.scrimage:scrimage-webp:4.2.0")
    api("com.twelvemonkeys.imageio:imageio-jpeg:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-core:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-metadata:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-webp:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-bmp:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-iff:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-pcx:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-pnm:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-sgi:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-tga:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-tiff:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-batik:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-pict:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-psd:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-icns:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-pdf:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-thumbsdb:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-hdr:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-clippath:3.11.0")
    api("com.twelvemonkeys.imageio:imageio-xwd:3.11.0")
    api("org.apache.xmlgraphics:batik-transcoder:1.17")
    api("org.apache.xmlgraphics:batik-rasterizer-ext:1.17")
    api("org.apache.xmlgraphics:batik-extension:1.17")
    api("org.apache.xmlgraphics:batik-anim:1.17")
    api("org.apache.xmlgraphics:batik-svggen:1.17")

    api("com.madgag:animated-gif-lib:1.4")
    api("net.java.dev.jna:jna:5.13.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.0")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = javaVersion.majorVersion
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

signing {
    useGpgCmd()
}

centralPortal {
    pom {
        packaging = "jar"
        name.set("BreninSul Simple Image Convertor")
        val repositoryName = project.name
        url.set("https://github.com/BreninSul/$repositoryName")
        description.set(
            """
            """.trimIndent(),
        )
        licenses {
            license {
                name.set("MIT License")
                url.set("http://opensource.org/licenses/MIT")
            }
        }
        scm {
            connection.set("scm:https://github.com/BreninSul/$repositoryName.git")
            developerConnection.set("scm:git@github.com:BreninSul/$repositoryName.git")
            url.set("https://github.com/BreninSul/$repositoryName")
        }
        developers {
            developer {
                id.set("BreninSul")
                name.set("BreninSul")
                email.set("brenimnsul@gmail.com")
                url.set("breninsul.github.io")
            }
        }
    }
}

tasks.jar {
    enabled = true
    archiveClassifier.set("")
}
