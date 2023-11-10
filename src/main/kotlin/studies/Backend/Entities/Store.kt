package studies.Backend.Entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.util.*

@Entity
data class Store (
    val storeID: UUID? = null,
    val storeName: String,
    @Column(name="CNPJ", unique=true)
    val storeCNPJ: String,
    val storeEmail: String,
    val storePassword: String,
    val storeWallet: String
) : User(storeID, storeName, storeEmail, storePassword, storeWallet)