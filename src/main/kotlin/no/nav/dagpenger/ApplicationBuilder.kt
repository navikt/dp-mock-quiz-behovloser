package no.nav.dagpenger

import no.nav.helse.rapids_rivers.RapidApplication
import no.nav.helse.rapids_rivers.RapidsConnection

internal class ApplicationBuilder(config: Map<String, String>) : RapidsConnection.StatusListener {

    private val rapidsConnection: RapidsConnection = RapidApplication.Builder(
        RapidApplication.RapidApplicationConfig.fromEnv(config)
    ).build()

    init {
        rapidsConnection.register(this)
        Behovmottak(rapidsConnection)
    }

    fun start() {
        rapidsConnection.start()
    }
}
