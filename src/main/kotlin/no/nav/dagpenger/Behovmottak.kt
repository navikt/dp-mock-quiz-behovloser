package no.nav.dagpenger

import mu.KotlinLogging
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
            validate { it.requireKey("@behov") }
            validate { it.rejectKey("@løsning") }
            validate { it.interestedIn("søknad_uuid") }
        }.register(this)
    }

    override fun onPacket(packet: JsonMessage, context: MessageContext) {
        val behov = packet["@behov"].single().asText()
        logger.info("Behov mottatt: $behov")
        when (behov) {
            "Barn" -> publiserLøsningForRegisterbarn(packet)
            else -> logger.info("Ikke støtte for følgende behov: $behov. Full JSON: ${packet.toJson()}")
        }
    }

    private fun publiserLøsningForRegisterbarn(packet: JsonMessage) {
        packet["@løsning"] = RegisterbarnBehov.svar()
        rapidsConnection.publish(packet.toJson())
        logger.info { "Sendte ut pakke: " + packet.toJson() }
    }
}
