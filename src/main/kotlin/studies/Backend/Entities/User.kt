package studies.Backend.Entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.hateoas.RepresentationModel
import java.io.Serializable

@Entity
data class User(
    //Valores como strings temporariamente!
    @Id
    @GeneratedValue(strategy=GenerationType.UUID)
    val ID :  String, //Funciona essa de usar o String com UUID?
    val Identifier : String, //Pode ser tanto cpf quanto cnpj, faz diferença? criar uma tipagem?
    val Email : String,
    val Name : String,
    val Password : String,
    val Wallet : String
) : RepresentationModel<User>(), Serializable
