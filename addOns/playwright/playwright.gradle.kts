import org.zaproxy.gradle.addon.AddOnStatus

version = "0.0.1"
description = "Run playwright tests in ZAP"

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
    }
}

crowdin {
    configuration {
        val resourcesPath = "org/zaproxy/addon/${zapAddOn.addOnId.get()}/resources/"
        tokens.put("%messagesPath%", resourcesPath)
        tokens.put("%helpPath%", resourcesPath)
    }
}

dependencies {
    var playwrightVersion = "1.40.0"
    implementation("com.microsoft.playwright:playwright:$playwrightVersion")
    implementation(libs.log4j.slf4j) {
        // Provided by ZAP.
        exclude(group = "org.apache.logging.log4j")
    }

    zapAddOn("network")

    testImplementation(project(":testutils"))
}
