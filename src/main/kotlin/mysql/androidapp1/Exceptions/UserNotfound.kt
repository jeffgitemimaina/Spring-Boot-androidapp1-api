package mysql.androidapp1.Exceptions


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.NOT_FOUND)
data class UserNotFound(override val message:String): RuntimeException()