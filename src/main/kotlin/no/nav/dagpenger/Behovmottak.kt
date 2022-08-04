package no.nav.dagpenger

import mu.KotlinLogging
import no.nav.helse.rapids_rivers.JsonMessage
import no.nav.helse.rapids_rivers.MessageContext
import no.nav.helse.rapids_rivers.RapidsConnection
import no.nav.helse.rapids_rivers.River

class Behovmottak(
    rapidsConnection: RapidsConnection,
    private val behovsvarFactory: BehovsvarFactory
) : River.PacketListener {

    companion object {
        private val logger = KotlinLogging.logger {}
    }

    init {
        River(rapidsConnection).apply {
            validate { it.demandValue("@event_name", "behov") }
            validate { it.requireKey("@behov") }
        }.register(this)
    }

    override fun onPacket(packet: JsonMessage, context: MessageContext) {
        val behov = packet["@behov"].single().asText()
        logger.info("Behov: $behov")

        when (behov) {
            "NySøknad" -> behovsvarFactory.nySøknadBehovsvar()
            "Barn" -> behovsvarFactory.registerbarnBehovsvar()
            else -> logger.info("Ikke støtte for følgende behov: $behov. Full JSON: ${packet.toJson()}")
        }
    }
}
