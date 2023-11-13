package studies.Backend.Controllers

import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studies.Backend.DTO.StoreDTO
import studies.Backend.Entities.Store
import studies.Backend.Repositories.StoreRepository
import java.io.Serializable
import java.util.*

@RestController
@RequestMapping("/store")
class StoreController {

    @Autowired
    lateinit var storeRepository: StoreRepository;

    @PostMapping
    fun saveStore(@RequestBody @Valid incomingStore: StoreDTO?): ResponseEntity<Store> {
        val storeToSave = incomingStore!!.let {
            Store(
                storeName = it.incomingName,
                CNPJ = it.incomingCnpj,
                storeEmail = it.incomingEmail,
                storePassword = it.incomingPassword,
                storeWallet = it.incomingWallet
            )
        }
        storeRepository.save(storeToSave)
        return ResponseEntity.status(HttpStatus.CREATED).body(storeToSave)
    }
    @GetMapping
    fun getAllStore(): ResponseEntity<List<Store>> {
        val storeList: List<Store> = storeRepository.findAll()
        return ResponseEntity.status(HttpStatus.OK).body<List<Store>>(storeList)
    }

    @GetMapping("{id}")
    fun getOneStore(@PathVariable(value = "id") id: Long): ResponseEntity<out Serializable> {
        val storeO: Optional<Store> = storeRepository.findById(id)
        if (storeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found.")
        }
        storeO.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(StoreController::class.java).getAllStore()).withRel("Store List"))
        return ResponseEntity.status(HttpStatus.OK).body(storeO.get())
    }

    @PutMapping("{id}")
    fun updateStore(
        @PathVariable(value = "id") id: Long,
        @RequestBody storeDto: @Valid StoreDTO?
    ): ResponseEntity<out Any> {
        val storeO: Optional<Store> = id.let { storeRepository.findById(it) }
        if (storeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found.")
        }
        val store: Store = storeO.get()
        if (storeDto != null) {
            BeanUtils.copyProperties(storeDto, store)
        }
        return ResponseEntity.status(HttpStatus.OK).body(storeRepository.save(store))
    }

    @DeleteMapping("/delete{id}")
    fun deleteStore(@PathVariable(value = "id") id: Long): ResponseEntity<String> {
        val storeO: Optional<Store> = storeRepository.findById(id)
        if (storeO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Store not found.")
        }
        storeRepository.delete(storeO.get())
        return ResponseEntity.status(HttpStatus.OK).body("Store deleted successfully.")
    }
}