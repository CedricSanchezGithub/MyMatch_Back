package org.mymatchback.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDate

fun main() {
    val matchs = listOf(
        MatchBean(date = LocalDate.of(2024, 3, 20), equipe1 = "Nantes", equipe2 = "MHB", score_equipe1 = 22, score_equipe2 = 30, status = false),
        MatchBean(date = LocalDate.of(2024, 3, 21), equipe1 = "MHB", equipe2 = "Nantes", score_equipe1 = 25, score_equipe2 = 28, status = false),
        MatchBean(date = LocalDate.of(2024, 3, 19), equipe1 = "HBLM", equipe2 = "MHB", score_equipe1 = 21, score_equipe2 = 29, status = false),
        MatchBean(date = LocalDate.of(2024, 3, 18), equipe1 = "Poitier", equipe2 = "MHB", score_equipe1 = 24, score_equipe2 = 28, status = false),
        MatchBean(date = LocalDate.of(2024, 3, 20), equipe1 = "HBLM", equipe2 = "MHB", score_equipe1 = 23, score_equipe2 = 27, status = false),
        MatchBean(date = LocalDate.of(2024, 3, 19), equipe1 = "Poitier", equipe2 = "MHB", score_equipe1 = 24, score_equipe2 = 23, status = false),
        MatchBean(date = LocalDate.of(2024, 3, 21), equipe1 = "Poitier", equipe2 = "PSG", score_equipe1 = 27, score_equipe2 = 22, status = false)
    )

    // Affichage des matchs pour vérification
    matchs.forEach { match ->
        println("${match.equipe1} vs ${match.equipe2} le ${match.date}: ${match.score_equipe1}-${match.score_equipe2}, Status: ${match.status}")
    }


}



@Entity
@Table(name = "match_table")

data class MatchBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var date: LocalDate = LocalDate.of(1988, 1,1),
    var equipe1: String = "Equipe 1",
    var equipe2: String = "Equipe 2",
    var score_equipe1: Int = 0,
    var score_equipe2: Int = 0,
    var status: Boolean = false)

@Repository
interface MatchRepository : JpaRepository<MatchBean, Long>

@Service
class MatchService(val matchRep:MatchRepository) {

    fun createMatch(equipe1:String?, equipe2:String?, date: LocalDate) : MatchBean {
        if(equipe1.isNullOrBlank()){
            throw Exception("equipe 1 missing")
        }
        if(equipe2.isNullOrBlank()){
            throw Exception("equipe 2 missing")
        }

        val match = MatchBean(null,date, equipe1, equipe2, 0, 0)
        matchRep.save(match)
        return match
    }
    fun add1Point(match: MatchBean, equipe:String):MatchBean{
        if(!match.status){
            throw Exception("Le match est finis")
        }else{
            if (equipe == match.equipe1){
                match.score_equipe1++
            }else{
                match.score_equipe2++
            }
        }
        matchRep.save(match)
        return match
    }

    fun returnLastSevenDays(matchs: List<MatchBean?>): List<MatchBean> {
        val ilYaSeptJours = LocalDate.now().minusDays(7)

        val matchsRecents = matchs.filterNotNull().filter { match ->
            match.date.isAfter(ilYaSeptJours) || match.date.isEqual(ilYaSeptJours)
        }
        println(matchsRecents)
        return matchsRecents
    }

}

