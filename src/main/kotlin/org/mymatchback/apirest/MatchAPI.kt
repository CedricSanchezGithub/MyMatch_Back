package org.mymatchback.apirest

import org.mymatchback.model.MatchBean
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/mymatch")
class MymatchAPI{

    var tabMatch = List<MatchBean?>(10) {null}

    // Une url pour créer un matche en donnant les 2 équipes et la date

    //http://localhost:8080/mymatch/create
    @PostMapping("/create")
    fun createMatch(@RequestBody equipe1: String, equipe2: String, date: LocalDate){



    }
}

