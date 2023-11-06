package studies.Backend.Controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    lateinit val personController :PersonController()

    @GetMapping
    fun getPerson(){
//        Person p = new Person();
    }
}