package org.mymatchback.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.time.LocalDate

@Entity
@Table(name = "match_table")

data class MatchBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null,
    var date: String = "1er Janvier",
    var equipe1: String = "Equipe 1",
    var equipe2: String = "Equipe 2",
    var score_equipe1: Int = 0,
    var score_equipe2: Int = 0,
    var status: Boolean = false)

@Repository
interface MatchRepository : JpaRepository<MatchBean, Long>

@Service
class MatchService(val matchRep:MatchRepository) {

    fun createMatch(equipe1:String?, equipe2:String?, date: String) : MatchBean {
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
    fun add1Point(match: MatchBean, equipe:Int):MatchBean{
        if(match.status==false){
            throw Exception("Le match est finis")
        }else{
            if (equipe == 1) match.score_equipe1++ else match.score_equipe2++
        }
        matchRep.save(match)
        return match
    }

}

