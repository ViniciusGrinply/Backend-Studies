package studies.backend.Entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import lombok.AllArgsConstructor
import org.springframework.hateoas.RepresentationModel
import java.io.Serializable

@Entity
@AllArgsConstructor //Presta atencao com como isso lida com ID, talvez gerado pela db?
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
