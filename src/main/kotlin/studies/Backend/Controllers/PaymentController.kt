package studies.Backend.Controllers

import jakarta.persistence.EntityManager
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studies.Backend.Entities.Payment
import studies.Backend.Repositories.PaymentRepository
import studies.Backend.Repositories.PersonRepository
import studies.Backend.Repositories.StoreRepository
import java.io.Serializable
import java.util.*

@RestController
@RequestMapping("/payment")
class PaymentController(val entityManager: EntityManager, val paymentRepository: PaymentRepository, val personRepository: PersonRepository, val storeRepository: StoreRepository) {

    @PostMapping
    fun makePayment(@RequestBody incomingPayment: Payment): ResponseEntity<String>{
        var payer = personRepository.findById(incomingPayment.payerId)
            .orElseThrow { throw IllegalArgumentException("User not found with id ${incomingPayment.payerId}")}

        if (incomingPayment.value > payer.personWallet) {
            return ResponseEntity("Insufficient funds", HttpStatus.BAD_REQUEST)
        }

        entityManager.createNativeQuery("UPDATE person SET person_wallet = person_wallet - :value WHERE person_ID = :payerId")
            .setParameter("value", incomingPayment.value)
            .setParameter("payerId", incomingPayment.payerId)
            .executeUpdate()

        entityManager.createNativeQuery("UPDATE store SET store_wallet = store_wallet + :value WHERE person_ID = :payeeId")
            .setParameter("value", incomingPayment.value)
            .setParameter("payeeId", incomingPayment.payeeId)
            .executeUpdate()

        paymentRepository.save(incomingPayment)
        return ResponseEntity("Payment processed successfully", HttpStatus.OK)
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