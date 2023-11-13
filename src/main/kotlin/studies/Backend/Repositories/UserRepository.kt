package studies.Backend.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import studies.Backend.Entities.User

@Repository
public interface UserRepository : JpaRepository<User, Long>{
}