package org.mymatchback.apirest

import org.mymatchback.model.MatchBean
import org.mymatchback.model.MatchService
import org.springframework.web.bind.annotation.*

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
    @PostMapping("/creatematch")
    fun createMatch(@RequestBody match: MatchBean){

        println("/save match = $match")
        match.date?.let { matchService.createMatch(date = it, equipe1 = match.equipe1, equipe2 = match.equipe2 ) }

    }

    //  Une url pour faire évoluer le score du matche
    //http://localhost:8080/mymatch/score

    @PostMapping("/score")
    fun add1Point(@RequestParam idMatch: Long, equipe:Int){
        matchService.add1Point(idMatch, equipe)
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
    //http://localhost:8080/mymatch/statusover
    @PostMapping("/statusover")
    fun setStatusOver(@RequestBody idMatch: Long) {
        return matchService.setStatusOver(idMatch)
    }

    @DeleteMapping("/deletematch/{id}")
    fun deleteMatch(@PathVariable id: Long) {
        matchService.deleteMatch(id)
    }

}

