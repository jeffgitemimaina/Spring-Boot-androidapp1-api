package mysql.androidapp1.Repository


import com.sun.jdi.Value
import mysql.androidapp1.Entities.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface UserRepository : JpaRepository<User, Long>{


    @Query(value = "SELECT * FROM Users WHERE first_name = :firstName ", nativeQuery = true)
    fun findUserByName(@Param("firstName") firstName: String): List<User>

    @Query(value = "SELECT * FROM users", nativeQuery = true)
    fun getAllUsers(): List<User>
    @Query(value = "SELECT * FROM users WHERE status = :status", nativeQuery = true)
    fun getUserByStatus(status: Boolean): List<User>
    @Query(value= "SELECT * FROM users WHERE image", nativeQuery = true)
    fun getProfileById(@Param("id") id :Long): ByteArray?
    //alternatively you can use the springboot function below
    //fun getUserByStatus(status: Boolean): List<User>

}
