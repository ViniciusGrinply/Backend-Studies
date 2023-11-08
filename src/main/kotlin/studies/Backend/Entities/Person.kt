package studies.Backend.Entities

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Person(@Id
             @GeneratedValue(strategy= GenerationType.UUID)
             val ID :  String,)