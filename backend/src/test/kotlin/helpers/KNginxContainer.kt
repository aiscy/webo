package online.senpai.helpers

import org.testcontainers.containers.NginxContainer

open class KNginxContainer(dockerImageName: String) : NginxContainer<KNginxContainer>(dockerImageName)
