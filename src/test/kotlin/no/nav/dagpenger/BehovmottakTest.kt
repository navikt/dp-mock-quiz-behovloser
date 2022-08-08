package no.nav.dagpenger

import no.nav.helse.rapids_rivers.testsupport.TestRapid
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class BehovmottakTest {

    private val testRapid = TestRapid()

    @Test
    fun `Fanger opp og besvarer NAV-behov om registerbarn fra Quiz`() {
        Behovmottak(testRapid)
        val søknadUuid = "41621ac0-f5ee-4cce-b1f5-88a79f25f1a5"

        testRapid.sendTestMessage(registerbarnBehov(søknadUuid))

        val registerbarnBehovSvar = testRapid.inspektør.message(0)

        assertEquals(søknadUuid, registerbarnBehovSvar["søknad_uuid"].asText())
        assertNotNull(registerbarnBehovSvar["@løsning"]["Barn"])
    }
}

// language=JSON
fun registerbarnBehov(søknadUuid: String) = """
    {
      "@event_name": "faktum_svar",
      "@opprettet": "2021-11-18T11:04:32.867824",
      "@id": "930e2beb-d394-4024-b713-dbeb6ad3d4bf",
      "søknad_uuid": "$søknadUuid",
      "identer": [
        {
          "id": "32542134",
          "type": "folkeregisterident",
          "historisk": false
        }
      ],
      "@behov": [
        "Barn"
      ],
      "fakta": [
        {
          "id": "10",
          "behov": "Barn",
          "type": "generator",
          "templates": [
            {
              "id": "11",
              "navn": "faktum.barn-fornavn-mellomnavn",
              "type": "tekst"
            },
            {
              "id": "11",
              "navn": "faktum.barn-etternavn",
              "type": "tekst"
            },
            {
              "id": "11",
              "navn": "faktum.barn-foedselsdato",
              "type": "localdate"
            },
            {
              "id": "11",
              "navn": "faktum.barn-statsborgerskap",
              "type": "localdate"
            }
          ]
        }
      ]
    }
""".trimIndent()
