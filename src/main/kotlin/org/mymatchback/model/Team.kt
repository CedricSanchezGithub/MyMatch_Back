package org.mymatchback.model

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Entity
@Table(name = "team_table")

data class TeamBean(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,
    var name: String?= "Default_Name1",
    var logo: String? = "Logo_Default",
)

@Repository
interface TeamRepository : JpaRepository<TeamBean, Long> {
    fun findAllByTeamOrderByName(name : Long) : List<TeamBean>
}

