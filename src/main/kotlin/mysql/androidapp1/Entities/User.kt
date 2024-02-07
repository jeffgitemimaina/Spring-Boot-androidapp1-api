package mysql.androidapp1.Entities

import jakarta.persistence.*


@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0,

        @Column(name = "fname")
        var firstName: String,

        @Column(name = "sname")
        var secondName: String,


        @Column(name = "email")
        var email: String,

        @Column(name = "pnumber")
        var pnumber : Long,

        @Column( name = "password")
        var password : String
)
