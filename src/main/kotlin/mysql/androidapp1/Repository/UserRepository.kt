package mysql.androidapp1.Repository


import mysql.androidapp1.Entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long>{


    @Query(value = "SELECT * FROM Users WHERE firstName = :firstName ", nativeQuery = true)
    fun findUserByName(@Param("firstName") firstName: String): List<User>

}
