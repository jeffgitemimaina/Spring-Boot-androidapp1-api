package mysql.androidapp1.Service

import mysql.androidapp1.Entities.User
import mysql.androidapp1.Exceptions.UserNotFound
import mysql.androidapp1.Repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.*
import javax.crypto.spec.SecretKeySpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec



@Service
class UserService @Autowired constructor(
    private val userRepository: UserRepository
) {


    private val secretKey = generateSecretKey()
    private val ivParameterSpec = generateIV()

    private fun generateSecretKey(): SecretKeySpec {
        val key = ByteArray(16) // 128 bits
        SecureRandom().nextBytes(key)
        return SecretKeySpec(key, "AES")
    }

    private fun generateIV(): IvParameterSpec {
        val iv = ByteArray(16) // 128 bits
        SecureRandom().nextBytes(iv)
        return IvParameterSpec(iv)
    }

    fun encrypt(text: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec)
        val encryptedBytes = cipher.doFinal(text.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    fun createUser(user: User): User {
        val encryptedPassword = encrypt(user.password)
        user.password = encryptedPassword
        return userRepository.save(user)
    }

    fun authenticate(email: String, password: String): Boolean {
        val user = userRepository.findByEmail(email)
        val encryptedPassword = encrypt(password)
        return user != null && user.password == encryptedPassword
    }


    fun updateUser(id: Long, updatedUser: User): User {
        val existingUser = userRepository.findById(id)
        return if (existingUser.isPresent) {
            val userToUpdate = existingUser.get()
            userToUpdate.apply {
                firstName = updatedUser.firstName
                secondName = updatedUser.secondName
                email = updatedUser.email
                pnumber = updatedUser.pnumber
                password = updatedUser.password
            }
            userRepository.save(userToUpdate)
        } else {
            throw UserNotFound("User with id $id not found")
        }
    }

    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    fun getUserByEmail(email: String): User {
        return userRepository.findByEmail(email) ?: throw UserNotFound("User with email $email not found")
    }

    fun getUserByPhoneNumber(phoneNumber: Long): User {
        return userRepository.findByPnumber(phoneNumber) ?: throw UserNotFound("User with phone number $phoneNumber not found")
    }
}
