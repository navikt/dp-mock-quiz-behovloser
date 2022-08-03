package no.nav.dagpenger

import mu.KotlinLogging

object BehovsvarFactory {

    private val logger = KotlinLogging.logger {}

    fun createNySøknadBehovsvar() {
        logger.info("Lag mock svar for dette!")
    }
}
