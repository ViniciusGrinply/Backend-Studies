package studies.Backend.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import studies.Backend.Entities.Person

@Repository
public interface StoreRepository: JpaRepository<Store, Long> {
}