package org.mymatchback.model

import java.time.LocalDate

data class MatchBean(var id:Long? = null,
                     var date: LocalDate,
                     var equipe1: String = "Equipe 1",
                     var equipe2: String = "Equipe 2",
                     var score_equipe1: Int = 0,
                     var score_equipe2: Int = 0,
                     var status: Boolean = false)