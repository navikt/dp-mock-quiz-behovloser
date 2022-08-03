package no.nav.dagpenger

import no.nav.helse.rapids_rivers.RapidApplication
import no.nav.helse.rapids_rivers.RapidsConnection

internal class ApplicationBuilder(config: Map<String, String>) : RapidsConnection.StatusListener {

    private val rapidsConnection: RapidsConnection = RapidApplication.Builder(
        RapidApplication.RapidApplicationConfig.fromEnv(config)
    ).build()

    private val behovmottak = Behovmottak(rapidsConnection, BehovsvarFactory)

    init {
        rapidsConnection.register(this)
    }

    fun start() {
        rapidsConnection.start()
    }
}
