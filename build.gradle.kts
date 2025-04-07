import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.net.URL

plugins {
    val kotlinVersion = "2.1.0"
    id("java")
    id("net.thebugmc.gradle.sonatype-central-portal-publisher") version "1.2.4"
    id("org.jetbrains.kotlin.jvm") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.kapt") version kotlinVersion
    id("org.jetbrains.dokka") version "2.0.0"
}

group = "io.github.breninsul"
version = "2.0.6"

val scrimageVersion = "4.3.0"
val twelvemonkeysVersion = "3.12.0"
val batikVersion = "1.18"


val javaVersion = JavaVersion.VERSION_17


tasks.named<Jar>("javadocJar") {
    from(tasks.named("dokkaJavadoc"))
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}
java {
    sourceCompatibility = javaVersion
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
    api("io.github.breninsul:io-stream-commons:1.0.0")
    api("io.github.breninsul:WebPDecoderJN:1.3-b")
    api("org.apache.pdfbox:pdfbox:3.0.3")
    api("org.apache.tika:tika-core:3.0.0")
    api("com.sksamuel.scrimage:scrimage-core:$scrimageVersion")
    api("com.sksamuel.scrimage:scrimage-format-png:$scrimageVersion")
    api("com.sksamuel.scrimage:scrimage-formats-extra:$scrimageVersion")
    api("com.sksamuel.scrimage:scrimage-hash:$scrimageVersion")
    api("com.sksamuel.scrimage:scrimage-filters:$scrimageVersion")
    api("com.sksamuel.scrimage:scrimage-webp:$scrimageVersion")
    api("com.twelvemonkeys.imageio:imageio-jpeg:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-core:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-metadata:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-webp:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-bmp:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-iff:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-pcx:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-pnm:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-sgi:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-tga:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-tiff:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-batik:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-pict:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-psd:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-icns:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-pdf:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-thumbsdb:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-hdr:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-clippath:$twelvemonkeysVersion")
    api("com.twelvemonkeys.imageio:imageio-xwd:$twelvemonkeysVersion")
    api("org.apache.xmlgraphics:batik-transcoder:$batikVersion")
    api("org.apache.xmlgraphics:batik-rasterizer-ext:$batikVersion")
    api("org.apache.xmlgraphics:batik-extension:$batikVersion")
    api("org.apache.xmlgraphics:batik-anim:$batikVersion")
    api("org.apache.xmlgraphics:batik-svggen:$batikVersion")
    api("com.ashampoo:kim:0.20.2")
    api("com.madgag:animated-gif-lib:1.4")
    api("net.java.dev.jna:jna:5.15.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.0")

}

tasks.withType<DokkaTask>().configureEach {
    val repositoryName = project.name
    dokkaSourceSets {
        named("main") {
            moduleName.set(project.name)
            includes.from("README.md")
            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/BreninSul/$repositoryName"))
                remoteLineSuffix.set("#L")
            }
        }
    }
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
