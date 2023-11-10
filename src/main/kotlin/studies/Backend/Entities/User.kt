package studies.Backend.Entities

import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel
import java.io.Serializable
import java.util.*

@Entity
abstract class User(
    @Column(name="id", unique=true, columnDefinition = "VARCHAR(36)")
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    val ID :  UUID? = null,
    @Column(name="name")
    val Name : String,
    @Column(name="email", unique=true)
    val Email : String,
    @Column(name="password")
    val Password : String,
    @Column(name="wallet")
    val Wallet : String
) : RepresentationModel<User>(), Serializable