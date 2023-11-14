package studies.Backend.Entities

import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel
import java.io.Serializable

@Entity
class User(
    @Column(name="id", unique=true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val ID :  Long? = null,
    @Column(name="name")
    val Name : String,
    @Column(name="email", unique=true)
    val Email : String,
    @Column(name="password")
    val Password : String,
    @Column(name="wallet")
    val Wallet : String
) : RepresentationModel<User>(), Serializable