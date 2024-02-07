package mysql.androidapp1.Controller
import mysql.androidapp1.Service.UserService
import mysql.androidapp1.Entities.User
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

    @PostMapping("/auth")
    fun authenticateUser(@RequestBody authRequest: AuthenticationRequest): ResponseEntity<Boolean> {
        val isAuthenticated = userService.authenticate(authRequest.email, authRequest.password)
        return ResponseEntity.ok(isAuthenticated)
    }



    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updatedUser: User): ResponseEntity<User> {
        val updatedUserResponse = userService.updateUser(id, updatedUser)
        return ResponseEntity.ok(updatedUserResponse)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Unit> {
        userService.deleteUser(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResponseEntity<User> {
        val user = userService.getUserByEmail(email)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/phone/{phoneNumber}")
    fun getUserByPhoneNumber(@PathVariable phoneNumber: Long): ResponseEntity<User> {
        val user = userService.getUserByPhoneNumber(phoneNumber)
        return ResponseEntity.ok(user)
    }
}

data class AuthenticationRequest(val email: String, val password: String)
