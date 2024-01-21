package mysql.androidapp1.Service


import mysql.androidapp1.Entities.User
import mysql.androidapp1.Exceptions.UserNotFound
import mysql.androidapp1.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(
        private val userRepository: UserRepository
) {
    fun createUser(user: User): User {
        return userRepository.save(user)
    }



    fun updateUser(userId: Long, newUser: User): User? {
        if (userRepository.existsById(userId)) {
            newUser.id = userId  // Set the ID of the new user
            return userRepository.save(newUser)
        } else {
            throw UserNotFound("user doesnt exist")
            // Handle the case when the user with the given ID doesn't exist
            return null
        }
    }

    fun getUser(firstName: String): List<User> {
        val foundUsers = userRepository.findUserByName(firstName)
        if (foundUsers.isEmpty()) {
            throw UserNotFound("No users found with the specified name.")
        }
        return foundUsers
    }



}
