package studies.backend.Entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Store (@Id
             @GeneratedValue(strategy= GenerationType.UUID)
             val ID :  String,)