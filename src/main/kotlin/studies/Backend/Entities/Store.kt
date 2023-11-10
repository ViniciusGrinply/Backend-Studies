package studies.Backend.Entities

import jakarta.persistence.Column
import java.util.*

data class Store (
    val storeID: UUID? = null,
    val storeName: String,
    @Column(name="CNPJ", unique=true)
    val CNPJ: String,
    val storeEmail: String,
    val storePassword: String,
    val storeWallet: String
) : User(storeID, storeName, storeEmail, storePassword, storeWallet)