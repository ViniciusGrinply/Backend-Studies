package studies.Backend.DTO

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

@JvmRecord
data class PaymentDTO (
    @field:NotNull @param:NotBlank val payerId:Long,
    @field:NotNull @param:NotBlank val payeeId:Long,
    @field:NotNull @param:NotBlank val value:Double
)