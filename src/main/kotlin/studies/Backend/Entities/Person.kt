package studies.Backend.Entities

import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel
import java.io.Serializable

@Entity
data class Person(
    @Column(name="person_ID", unique=true)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val personID :  Long? = null,

    @Column(name = "person_name")
    val personName: String,

    @Column(name="cpf", unique=true)
    val personCPF: String,

    @Column(name="person_email", unique=true)
    val personEmail: String,

    @Column(name="person_password")
    val personPassword: String,

    @Column(name="person_wallet")
    val personWallet: String

) : RepresentationModel<Person>(), Serializable