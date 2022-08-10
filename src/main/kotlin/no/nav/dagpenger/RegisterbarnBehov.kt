package no.nav.dagpenger

import java.util.UUID

object RegisterbarnBehov {
    /* NB: id på fakta må korrespondere til id på fakta i dp-quiz. Ellers vil FaktumSvarService i dp-quiz avslå svaret*/
    // language=JSON
    fun svar(søknadUuid: String) = """
    {
      "@event_name": "faktum_svar",
      "@opprettet": "2021-11-18T11:04:32.867824",
      "@id": "930e2beb-d394-4024-b713-dbeb6ad3d4bf",
      "@behovId": "${UUID.randomUUID()}",
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
          "id": "1008",
          "behov": "Barn",
          "type": "generator",
          "templates": [
            {
              "id": "1009",
              "navn": "faktum.barn-fornavn-mellomnavn",
              "type": "tekst"
            },
            {
              "id": "1010",
              "navn": "faktum.barn-etternavn",
              "type": "tekst"
            },
            {
              "id": "1011",
              "navn": "faktum.barn-foedselsdato",
              "type": "localdate"
            },
            {
              "id": "1012",
              "navn": "faktum.barn-statsborgerskap",
              "type": "land"
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
}
