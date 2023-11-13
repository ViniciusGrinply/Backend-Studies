package studies.Backend.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class PersonDTO (
    @field:NotNull @param:NotBlank val incomingName: String,
    @field:NotNull @param:NotBlank val incomingCpf: String,
    @field:NotNull @param:NotBlank val incomingEmail: String,
    @field:NotNull @param:NotBlank val incomingPassword: String,
    @field:NotNull @param:NotBlank val incomingWallet: String
)