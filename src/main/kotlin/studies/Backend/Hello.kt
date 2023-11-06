package studies.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Hello {

    @GetMapping("/")
    fun Hello(): String {
        return "Leave me alone!"
    }
}