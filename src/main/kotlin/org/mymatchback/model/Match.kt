package org.mymatchback.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody


@Entity
@Table(name = "match_table")

data class MatchBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var date: Long? = null,
    var equipe1: String = "Equipe 1",
    var equipe2: String = "Equipe 2",
    var score_equipe1: Int = 0,
    var score_equipe2: Int = 0,
    var status: Boolean = false)

@Repository
interface MatchRepository : JpaRepository<MatchBean, Long>{

    fun findAllByDateAfterOrderByDateDesc(date: Long):  List<MatchBean>

    fun findByIdMatches(idMatch: Long):MatchBean

}

@Service
class MatchService(val matchRep:MatchRepository) {

    //Créer un match avec nom de la première équipe, nom de la seconde équipe, la date du jour, initialisé à 0 - 0
    fun createMatch(equipe1:String?, equipe2:String?, date: Long) : MatchBean {
        if(equipe1.isNullOrBlank()){
            throw Exception("equipe 1 missing")
        }
        if(equipe2.isNullOrBlank()){
            throw Exception("equipe 2 missing")
        }

        val match = MatchBean(null, date, equipe1, equipe2, 0, 0)

        matchRep.save(match)

        return match
    }

    //Incrémente 1 point à l'équipe
    fun add1Point(idMatch: Long, equipe:Int):MatchBean{
        var match= matchRep.findByIdMatches(idMatch)
        if(!match.status){
            throw Exception("Le match est finis")
        }else{
            if (equipe == 1) match.score_equipe1++ else match.score_equipe2++
        }
        matchRep.save(match)
        return match
    }


    //Retourne les 7 derniers matchs trié par date
    fun last7Dayz(date: Long) = matchRep.findAllByDateAfterOrderByDateDesc(date = date)

    //Match terminé
    fun setStatusOver(match: MatchBean) {
        match.status = true
        println("statusover : ${match.status}" )
    }
    fun setStatusNotOver(match: MatchBean) {
        match.status = false
        println("statusNotover : ${match.status}" )

    }
}

