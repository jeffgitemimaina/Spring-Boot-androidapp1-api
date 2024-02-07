package mysql.androidapp1.Repository


import mysql.androidapp1.Entities.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>{
    fun findByEmail(email: String): User?
    fun findByPnumber(pnumber: Long): User?


}
