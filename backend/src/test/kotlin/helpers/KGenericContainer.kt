package online.senpai.helpers

import org.testcontainers.containers.GenericContainer

open class KGenericContainer(dockerImageName: String) : GenericContainer<KGenericContainer>(dockerImageName) //  Kill me
