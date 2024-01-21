package mysql.androidapp1.Exceptions


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.UNAUTHORIZED)
data class InvalidCredentials(override val message : String): RuntimeException() {
}