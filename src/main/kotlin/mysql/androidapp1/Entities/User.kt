package mysql.androidapp1.Entities

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        val name: String,
        val age: Int
)
