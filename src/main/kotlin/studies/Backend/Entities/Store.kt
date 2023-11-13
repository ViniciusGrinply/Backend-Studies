package studies.Backend.Entities

import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel
import java.io.Serializable

@Entity
data class Store (
    @Column(name="person_ID", unique=true)
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    val storeID: Long? = null,

    @Column(name = "store_name")
    val storeName: String,

    @Column(name="CNPJ", unique=true)
    val CNPJ: String,

    @Column(name = "store_email")
    val storeEmail: String,

    @Column(name = "store_password")
    val storePassword: String,

    @Column(name = "store_wallet")
    val storeWallet: String
) : RepresentationModel<Person>(), Serializable