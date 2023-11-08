package studies.Backend.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import studies.Backend.Entities.Person
import java.util.*

@Repository
public interface PersonRepository : JpaRepository<Person, UUID>{
}