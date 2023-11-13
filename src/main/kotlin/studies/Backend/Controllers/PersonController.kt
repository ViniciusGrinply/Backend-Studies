package studies.Backend.Controllers

import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studies.Backend.DTO.PersonDTO
import studies.Backend.Entities.Person
import studies.Backend.Repositories.PersonRepository
import java.io.Serializable
import java.util.*


@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    lateinit var personRepository : PersonRepository;

    @PostMapping
    fun savePerson(@RequestBody @Valid incomingPerson: PersonDTO?): ResponseEntity<Person> {
        val personToSave = incomingPerson!!.let {
            Person(
                personName = it.incomingName,
                personCPF = it.incomingCpf,
                personEmail = it.incomingEmail,
                personPassword = it.incomingPassword,
                personWallet = it.incomingWallet
            )
        }
        personRepository.save(personToSave)
        return ResponseEntity.status(HttpStatus.CREATED).body(personToSave)
    }

    @GetMapping
    fun getAllPerson(): ResponseEntity<List<Person>> {
        val peopleList: List<Person> = personRepository.findAll()
        return ResponseEntity.status(HttpStatus.OK).body<List<Person>>(peopleList)
    }
    @GetMapping("{id}")
    fun getOnePerson(@PathVariable(value = "id") id: Long): ResponseEntity<out Serializable> {
        val PesonO: Optional<Person> = personRepository.findById(id)
        if (PesonO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.")
        }
        PesonO.get().add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonController::class.java).getAllPerson()).withRel("Person List"))
        return ResponseEntity.status(HttpStatus.OK).body(PesonO.get())
    }

    @DeleteMapping("/delete{id}")
    fun deletePerson(@PathVariable(value = "id") id: Long): ResponseEntity<String> {
        val personO: Optional<Person> = personRepository.findById(id)
        if (personO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.")
        }
        personRepository.delete(personO.get())
        return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully.")
    }
    @PutMapping("{id}")
    fun updatePerson(
        @PathVariable(value = "id") id: Long,
        @RequestBody personDto: @Valid PersonDTO?
    ): ResponseEntity<out Any> {
        val personO: Optional<Person> = id.let { personRepository.findById(it) }
        if (personO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Person not found.")
        }
        val person: Person = personO.get()
        if (personDto != null) {
            BeanUtils.copyProperties(personDto, person)
        }
        return ResponseEntity.status(HttpStatus.OK).body(personRepository.save(person))
    }
}

