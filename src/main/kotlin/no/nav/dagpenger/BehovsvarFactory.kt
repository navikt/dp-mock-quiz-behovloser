package no.nav.dagpenger

import mu.KotlinLogging

object BehovsvarFactory {

    private val logger = KotlinLogging.logger {}

    fun nySøknadBehovsvar() = logger.info("Lag svar for ny søknad")

    fun registerbarnBehovsvar() = logger.info("Lag svar for registerbarn")
}
