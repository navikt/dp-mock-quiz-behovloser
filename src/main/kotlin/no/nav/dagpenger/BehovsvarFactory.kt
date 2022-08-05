package no.nav.dagpenger

import mu.KotlinLogging

object BehovsvarFactory {

    private val logger = KotlinLogging.logger {}

    fun nySøknadBehovsvar() = logger.info("Lag svar for ny søknad")

    fun registerbarnBehovsvar() = registerbarnBehovLøsning()
}

// language=JSON
fun registerbarnBehovLøsning() = """
    {
      "@event_name": "faktum_svar",
      "@opprettet": "2021-11-18T11:04:32.867824",
      "@id": "930e2beb-d394-4024-b713-dbeb6ad3d4bf",
      "søknad_uuid": "41621ac0-f5ee-4cce-b1f5-88a79f25f1a5",
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
      ],
      "system_read_count": 0,
      "@løsning": {
        "Barn": [
          {
            "faktum.barn-etternavn": "Testen",
            "faktum.barn-foedselsdato": "2022-08-04",
            "faktum.barn-statsborgerskap": "NOR",
            "faktum.barn-fornavn-mellomnavn": "Test"
          },
          {
            "faktum.barn-etternavn": "Testen",
            "faktum.barn-foedselsdato": "2022-08-04",
            "faktum.barn-statsborgerskap": "NOR",
            "faktum.barn-fornavn-mellomnavn": "Test Super"
          }
        ]
      }
    }
""".trimIndent()
