package studies.Backend.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.*

@Entity
data class Person(
    val personID: UUID? = null,
    @Column(name="CPF", unique=true)
    val CPF: String,
    val personName: String,
    val personEmail: String,
    val personPassword: String,
    val personWallet: String
) : User(personID, personName, personEmail, personPassword, personWallet)