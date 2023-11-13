package studies.Backend.Controllers

import jakarta.validation.Valid
import org.springframework.beans.BeanUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studies.Backend.DTO.UserDTO
import studies.Backend.Entities.User
import studies.Backend.Repositories.UserRepository
import java.io.Serializable
import java.util.*

//TODO:create a getby/name, email, whatever
@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userRepository : UserRepository;
//
//    @PostMapping
//    fun saveUser(@RequestBody @Valid userDTO: UserDTO?): ResponseEntity<User> {
//        val user = userDTO?.let {
//            User(
//                Identifier = it.Identifier,
//                Email = it.Email,
//                Name = it.Name,
//                Password = it.Password,
//                Wallet = it.Wallet
//            )
//        }
//        if (userDTO != null && user != null) {
//            BeanUtils.copyProperties(userDTO, user)
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(user?.let { userRepository.save(it) })
//    }


    @GetMapping("/all")
    fun getAllUsers(): ResponseEntity<List<User>> {
        val usersList: List<User> = userRepository.findAll()
        return ResponseEntity.status(HttpStatus.OK).body<List<User>>(usersList)
    }

    @GetMapping("{id}")
    fun getOneUser(@PathVariable(value = "id") id: Long): ResponseEntity<out Serializable> {
        val UserO: Optional<User> = userRepository.findById(id)
        if (UserO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.")
        }
        UserO.get().add(linkTo(methodOn(UserController::class.java).getAllUsers()).withRel("Users List"))
        return ResponseEntity.status(HttpStatus.OK).body(UserO.get())
    }

    @DeleteMapping("/delete{id}")
    fun deleteUser(@PathVariable(value = "id") id: Long): ResponseEntity<String> {
        val userO: Optional<User> = userRepository.findById(id)
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.")
        }
        userRepository.delete(userO.get())
        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.")
    }

    @PutMapping("{id}")
    fun updateUser(
        @PathVariable(value = "id") id: Long,
        @RequestBody userDto: @Valid UserDTO?
    ): ResponseEntity<out Any> {
        val userO: Optional<User> = id.let { userRepository.findById(it) }
        if (userO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.")
        }
        val user: User = userO.get()
        if (userDto != null) {
            BeanUtils.copyProperties(userDto, user)
        }
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user))
    }
}