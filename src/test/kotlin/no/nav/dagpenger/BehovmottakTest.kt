package no.nav.dagpenger

import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.Test
import java.util.UUID

internal class BehovmottakTest {

    private val testRapid = TestRapid()
    private val behovsvarFactory = mockk<BehovsvarFactory>(relaxed = true)

    @Test
    fun `Fanger opp og besvarer NySøknad behov fra Quiz med et mock-svar`() {
        Behovmottak(testRapid, behovsvarFactory)

        testRapid.sendTestMessage(nySøknadBehovUtenLøsning(UUID.randomUUID().toString()))

        verify { behovsvarFactory.nySøknadBehovsvar() }
        confirmVerified(behovsvarFactory)
    }

    @Test
    fun `Fanger opp og besvarer NAV-behov om registerbarn fra Quiz`() {
        Behovmottak(testRapid, behovsvarFactory)

        testRapid.sendTestMessage(registerbarnBehov())

        verify { behovsvarFactory.registerbarnBehovsvar() }
        confirmVerified(behovsvarFactory)
    }

    companion object {

        // language=JSON
        fun registerbarnBehov() = """ 
        {
          "@event_name": "behov",
          "@behovId": "84a03b5b-7f5c-4153-b4dd-57df041aa30d",
          "@behov": [
            "Barn"
          ],
          "ident": "12345678912",
          "søknad_uuid": "${UUID.randomUUID()}",
          "@id": "cf3f3303-121d-4d6d-be0b-5b2808679a79",
          "@opprettet": "2022-03-30T12:19:08.418821",
          "system_read_count": 0,
          "system_participating_services": [
            {
              "id": "cf3f3303-121d-4d6d-be0b-5b2808679a79",
              "time": "2022-03-30T12:19:08.418821"
            }
          ]
        }
        """.trimMargin()

        // language=JSON
        fun nySøknadBehovsløsning(søknadUuid: String) = """
        {
          "@event_name": "behov",
          "@behovId": "84a03b5b-7f5c-4153-b4dd-57df041aa30d",
          "@behov": [
            "NySøknad"
          ],
          "ident": "12345678912",
          "søknad_uuid": "$søknadUuid",
          "NySøknad": {},
          "@id": "cf3f3303-121d-4d6d-be0b-5b2808679a79",
          "@opprettet": "2022-03-30T12:19:08.418821",
          "system_read_count": 0,
          "system_participating_services": [
            {
              "id": "cf3f3303-121d-4d6d-be0b-5b2808679a79",
              "time": "2022-03-30T12:19:08.418821"
            }
          ],
          "@løsning": {"NySøknad": "$søknadUuid"}
        }
        """.trimMargin()

        // language=JSON
        fun nySøknadBehovUtenLøsning(søknadUuid: String) = """
        {
          "@event_name": "behov",
          "@behovId": "84a03b5b-7f5c-4153-b4dd-57df041aa30d",
          "@behov": [
            "NySøknad"
          ],
          "ident": "12345678912",
          "søknad_uuid": "$søknadUuid",
          "NySøknad": {},
          "@id": "cf3f3303-121d-4d6d-be0b-5b2808679a79",
          "@opprettet": "2022-03-30T12:19:08.418821",
          "system_read_count": 0, 
          "system_participating_services": [
            {
              "id": "cf3f3303-121d-4d6d-be0b-5b2808679a79",
              "time": "2022-03-30T12:19:08.418821"
            }
          ]
        }
        """.trimMargin()
    }
}
