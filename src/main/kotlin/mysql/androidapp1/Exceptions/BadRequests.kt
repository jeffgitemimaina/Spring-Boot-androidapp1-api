package mysql.androidapp1.Exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(HttpStatus.BAD_REQUEST)
data class BadRequest(override val message: String?): RuntimeException()
