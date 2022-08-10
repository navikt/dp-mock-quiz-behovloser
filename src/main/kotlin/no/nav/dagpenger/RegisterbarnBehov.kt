package no.nav.dagpenger

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDate

object RegisterbarnBehov {
    fun svar() = mapOf(
        "Barn" to listOf(
            Barn(
                fornavn = "Test",
                mellomnavn = "Testern",
                etterNavn = "Mockesen",
                fødselsdato = LocalDate.now().minusYears(17L),
                statsborgerskap = "NOR"
            ),
            Barn(
                fornavn = "John",
                mellomnavn = "I know nothing",
                etterNavn = "Snow",
                fødselsdato = LocalDate.now().minusYears(5L),
                statsborgerskap = "NOR"
            )
        )
    )

    data class Barn(
        private val fornavn: String,
        private val mellomnavn: String?,
        @JsonProperty("faktum.barn-etternavn")
        val etterNavn: String,
        @JsonProperty("faktum.barn-foedselsdato")
        val fødselsdato: LocalDate,
        @JsonProperty("faktum.barn-statsborgerskap")
        val statsborgerskap: String,
        @JsonProperty("faktum.barn-fornavn-mellomnavn")
        val forNavnMellomNavn: String = listOf(fornavn, mellomnavn).filterNot(String?::isNullOrBlank).joinToString(" ")
    )
}
