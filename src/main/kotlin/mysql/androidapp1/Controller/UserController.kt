package mysql.androidapp1.Controller

import mysql.androidapp1.Entities.User
import mysql.androidapp1.Exceptions.UserNotFound
import mysql.androidapp1.Service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController @Autowired constructor(
        private val userService: UserService
) {

    @PostMapping
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.createUser(user)
        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @PutMapping("/{userId}")
    fun updateUser(
            @PathVariable userId: Long,
            @RequestBody newUser: User
    ): ResponseEntity<User> {
        val updatedUser = userService.updateUser(userId, newUser)
        return if (updatedUser != null) {
            ResponseEntity(updatedUser, HttpStatus.OK)
        } else {
          throw UserNotFound("ngui iyo ndiriho ")
        }
    }
}
