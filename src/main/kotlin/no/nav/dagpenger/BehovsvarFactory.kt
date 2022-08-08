package no.nav.dagpenger

object BehovsvarFactory {

    fun registerbarnBehovsvar(søknadUuid: String) = RegisterbarnBehov.svar(søknadUuid)
}
