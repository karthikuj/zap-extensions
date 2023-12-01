import org.zaproxy.gradle.addon.AddOnPlugin
import org.zaproxy.gradle.addon.AddOnStatus

version = "0.0.1"
description = "Run playwright tests in ZAP"

val playwright by configurations.creating
configurations.api { extendsFrom(playwright) }

zapAddOn {
    addOnName.set("Playwright")
    addOnStatus.set(AddOnStatus.ALPHA)

    manifest {
        author.set("Karthik UJ (@5up3r541y4n)")
        url.set("https://www.5up3r541y4n.tech/")

        dependencies {
            addOns {
                register("network") {
                    version.set(">=0.2.0")
                }
            }
        }

        bundledLibs {
            libs.from(playwright)
        }
    }
}

crowdin {
    configuration {
        val resourcesPath = "org/zaproxy/addon/${zapAddOn.addOnId.get()}/resources/"
        tokens.put("%messagesPath%", resourcesPath)
        tokens.put("%helpPath%", resourcesPath)
    }
}

tasks.named(AddOnPlugin.GENERATE_MANIFEST_TASK_NAME) {
    dependsOn(tasks.withType<JavaExec>())
}

tasks {

    register<JavaExec>("installDrivers") {
        classpath(sourceSets["test"].runtimeClasspath)
        mainClass.set("org.zaproxy.addon.playwright.InstallDrivers")
    }
}

dependencies {
    var playwrightVersion = "1.40.0"
    playwright("com.microsoft.playwright:playwright:$playwrightVersion")
    var playwrightDriverBundleVersion = "1.40.0"
    playwright("com.microsoft.playwright:playwright:$playwrightDriverBundleVersion")
    implementation(libs.log4j.slf4j) {
        // Provided by ZAP.
        exclude(group = "org.apache.logging.log4j")
    }

    zapAddOn("network")

    testImplementation(project(":testutils"))
}
