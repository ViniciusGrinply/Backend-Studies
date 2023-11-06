package studies.Backend.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class UserDTO(
    @field:NotNull @param:NotBlank val Name: String,
    @field:NotNull @param:NotBlank val ID: String,
    @field:NotNull @param:NotBlank val Identifier: String,
    @field:NotNull @param:NotBlank val Email: String,
    @field:NotNull @param:NotBlank val Password: String,
    @field:NotNull @param:NotBlank val Wallet: String
)