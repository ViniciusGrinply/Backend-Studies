package studies.Backend.Controllers

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studies.Backend.DTO.PaymentDTO
import studies.Backend.Entities.Payment
import studies.Backend.Repositories.PaymentRepository
import studies.Backend.Repositories.PersonRepository
import studies.Backend.Repositories.StoreRepository
import java.io.Serializable
import java.util.*

@RestController
@RequestMapping("/payment")
class PaymentController(val storeRepository: StoreRepository, val paymentRepository: PaymentRepository, val personRepository: PersonRepository) {

    @PostMapping
    fun makePayment(@RequestBody incomingPayment: PaymentDTO): ResponseEntity<PaymentDTO> {
        val payer = personRepository.findById(incomingPayment.payerId)
            .orElseThrow { throw IllegalArgumentException("User not found with id ${incomingPayment.payerId}") }

        val payee = storeRepository.findById(incomingPayment.payeeId)
            .orElseThrow { throw IllegalArgumentException("Store not found with id ${incomingPayment.payeeId}") }

        if (incomingPayment.value > payer.personWallet) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(incomingPayment)
        }

        val updatedPayer = payer.copy(personWallet = payer.personWallet - incomingPayment.value)
        val updatedPayee = payee.copy(storeWallet = payee.storeWallet + incomingPayment.value)
        val payment = Payment(
            payerId = incomingPayment.payerId,
            payeeId = incomingPayment.payeeId,
            value = incomingPayment.value
        )
        personRepository.save(updatedPayer)
        storeRepository.save(updatedPayee)
        paymentRepository.save(payment)

        return ResponseEntity.status(HttpStatus.CREATED).body(incomingPayment)
    }


    @GetMapping
    fun getAllPayment(): ResponseEntity<List<Payment>> {
        val paymentList: List<Payment> = paymentRepository.findAll()
        return ResponseEntity.status(HttpStatus.OK).body<List<Payment>>(paymentList)
    }
    @GetMapping("{id}")
    fun getOnePayment(@PathVariable(value = "id") id: Long): ResponseEntity<out Serializable> {
        val PaymentO: Optional<Payment> = paymentRepository.findById(id)
        if (PaymentO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payment not found.")
        }
        PaymentO.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PaymentController::class.java).getAllPayment()).withRel("Payment List"))
        return ResponseEntity.status(HttpStatus.OK).body(PaymentO.get())
    }
}