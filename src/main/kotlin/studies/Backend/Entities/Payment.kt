package studies.Backend.Entities

import jakarta.persistence.*
import org.springframework.hateoas.RepresentationModel
import java.io.Serializable

@Entity
class Payment (
    @Column(name="person_ID", unique=true)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    val paymentID: Long? = null,

    @Column (name = "payer")
    val payerId : Long,

    @Column (name = "payee")
    val payeeId : Long,

    @Column (name = "value")
    val value : Double
) : RepresentationModel<Payment>(), Serializable