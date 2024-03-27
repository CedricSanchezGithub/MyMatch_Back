package org.mymatchback.apirest

import org.mymatchback.model.MatchBean
import org.mymatchback.model.MatchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

const val SEVENDAYZ = 604_800_000

@RestController
@RequestMapping("/mymatch")
class MymatchAPI(val matchService: MatchService){

    @GetMapping("/test")
    fun testHello(){
        println("hello la team")
    }

    // Une url pour créer un matche en donnant les 2 équipes et la date
    //http://localhost:8080/mymatch/create
    @PostMapping("/create")
    fun createMatch(@RequestBody match: MatchBean){

        println("/save match = $match")
        match.date?.let { matchService.createMatch(equipe1 = match.equipe1, equipe2 = match.equipe2, date = it) }

    }

    //  Une url pour faire évoluer le score du matche
    //http://localhost:8080/mymatch/score

    @PostMapping("/score")
    fun add1Point(@RequestBody match: MatchBean,equipe:Int){
        println("/score : ${matchService.add1Point(match, equipe)}")
        matchService.add1Point(match, equipe)
    }

    //   Une url pour récupérer la liste des matches des 7 derniers jours avec les plus récents en 1er
    //http://localhost:8080/mymatch/7dayz

    @GetMapping("/7dayz")
    fun lastSevenDayz() : List<MatchBean>{
        val THISDAY = System.currentTimeMillis()
        val daySearch = THISDAY - SEVENDAYZ
        return matchService.last7Dayz(date = daySearch)
    }

    //Déclarer le status du match
    //http://localhost:8080/mymatch/status
    @GetMapping("/statusover")
    fun setStatusOver(@RequestBody match: MatchBean) {
        match.status = true
        println("statusover : ${match.status}" )
    }
    @GetMapping("/statusNotover")
    fun setStatusNotOver(@RequestBody match: MatchBean) {
        match.status = false
        println("statusNotover : ${match.status}" )

    }
}

