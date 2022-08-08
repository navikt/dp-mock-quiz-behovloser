package no.nav.dagpenger

import mu.KotlinLogging
import no.nav.dagpenger.BehovsvarFactory.registerbarnBehovsvar
import no.nav.helse.rapids_rivers.JsonMessage
import no.nav.helse.rapids_rivers.MessageContext
import no.nav.helse.rapids_rivers.RapidsConnection
import no.nav.helse.rapids_rivers.River

class Behovmottak(
    private val rapidsConnection: RapidsConnection
) : River.PacketListener {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    init {
        River(rapidsConnection).apply {
            validate { it.requireKey("@behov", "søknad_uuid") }
        }.register(this)
    }

    override fun onPacket(packet: JsonMessage, context: MessageContext) {
        val behov = packet["@behov"].single().asText()
        val søknadUuid = packet["søknad_uuid"].asText()
        logger.info("Behov mottatt: $behov")

        when (behov) {
            "Barn" -> rapidsConnection.publish(
                registerbarnBehovsvar(søknadUuid)
            ).also { logger.info { "Besvarte $behov behov" } }
            else -> logger.info("Ikke støtte for følgende behov: $behov. Full JSON: ${packet.toJson()}")
        }
    }
}
