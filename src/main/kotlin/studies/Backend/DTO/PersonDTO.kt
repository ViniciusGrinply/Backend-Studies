package studies.Backend.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class PersonDTO (
    @field:NotNull @param:NotBlank val CPF: String,
    @field:NotNull @param:NotBlank val name: String,
    @field:NotNull @param:NotBlank val email: String,
    @field:NotNull @param:NotBlank val password: String,
    @field:NotNull @param:NotBlank val wallet: String
)