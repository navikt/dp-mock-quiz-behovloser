package no.nav.dagpenger

import com.natpryce.konfig.ConfigurationMap
import com.natpryce.konfig.ConfigurationProperties
import com.natpryce.konfig.EnvironmentVariables
import com.natpryce.konfig.overriding

internal object Configuration {

    const val appName = "dp-mock-quiz-behovloser"

    private val defaultProperties = ConfigurationMap(
        mapOf(
            "RAPID_APP_NAME" to "dp-mock-quiz-behovloser",
            "KAFKA_CONSUMER_GROUP_ID" to "dp-mock-quiz-behovloser-v1",
            "KAFKA_RAPID_TOPIC" to "teamdagpenger.rapid.v1",
            "KAFKA_RESET_POLICY" to "latest"
        )
    )
    val properties = ConfigurationProperties.systemProperties() overriding EnvironmentVariables() overriding defaultProperties

    val config: Map<String, String> = properties.list().reversed().fold(emptyMap()) { map, pair ->
        map + pair.second
    }
}
