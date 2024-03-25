package org.mymatchback.apirest

import org.mymatchback.model.MatchBean
import org.mymatchback.model.MatchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/mymatch")
class MymatchAPI(val matchService: MatchService){

    var tabMatch = List<MatchBean?>(10) {null}

    // Une url pour créer un matche en donnant les 2 équipes et la date

    //http://localhost:8080/mymatch/create
    @PostMapping("/create")
    fun createMatch(@RequestBody match: MatchBean){

        println("/save match = $match")
        matchService.createMatch(equipe1 = match.equipe1, equipe2 = match.equipe2, date = match.date)

    }

    //  Une url pour faire évoluer le score du matche ou le déclarer fini
    @PostMapping("/score")
    fun add1Point(@RequestBody match: MatchBean,equipe:String){
        println("/score : ${matchService.add1Point(match, equipe)}")
        matchService.add1Point(match, equipe)
    }
    //   Une url pour récupérer la liste des matches des 7 derniers jours avec les plus récents en 1er

    @GetMapping("/7dayz")
    fun lastSevenDayz() : List<MatchBean?>{


       matchService.returnLastSevenDays(tabMatch)

        return tabMatch
    }


}

