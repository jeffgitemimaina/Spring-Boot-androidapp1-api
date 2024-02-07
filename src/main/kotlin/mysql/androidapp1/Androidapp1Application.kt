package mysql.androidapp1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
class Androidapp1Application

fun main(args: Array<String>) {
	runApplication<Androidapp1Application>(*args)
}
