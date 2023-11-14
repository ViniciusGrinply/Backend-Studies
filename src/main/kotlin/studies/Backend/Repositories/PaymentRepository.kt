package studies.Backend.Repositories

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import studies.Backend.Entities.Payment

@Repository
public interface PaymentRepository :JpaRepository<Payment, Long>{
}