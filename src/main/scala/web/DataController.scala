package web

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import models.DataService
import models.conditionoccurrence.{ConditionOccurrenceService, ConditionOccurrence}
import models.death.DeathService
import models.observation.{ObservationService, Observation}
import models.observationperiod.ObservationPeriodService
import models.person.{Person, PersonService}
import org.apache.spark.sql.Dataset
import org.apache.spark.sql._
import utils.NumberUtils

@Singleton
class DataController @Inject() (personService: PersonService,
                                observationService: ObservationService,
                                deathService: DeathService,
                               observationPeriodService: ObservationPeriodService,
                                conditionOccurrenceService: ConditionOccurrenceService) extends Controller {

  get("/person") { request: Request =>

//    var persons = Array[Person]()
//    if (request.containsParam("genderId")) {
//      val genderId = NumberUtils.toLong(request.params("genderId")).get
//      persons = personService.findByGenderIdWithLimit(genderId, 20)
//    }
//    else {
//      persons = personService.findWithLimit(20)
//    }

    var filters = Array[String]();

//    if (request.containsParam("page")) {
//      val page = NumberUtils.toLong(request.params("page")).get
//      filters :+ "partition_id == " + page
//    }
    if (request.containsParam("genderId")) {
      val genderId = NumberUtils.toLong(request.params("genderId")).get
      filters = filters :+ "gender_concept_id == " + genderId
    }
    if (request.containsParam("dead")) {
      val isDead = request.params("dead") == "true"
      filters = filters :+ "death_date " + (if (isDead) "IS NOT" else "IS") + " NULL"
    }

    personService.findByFilters(filters)
  }

  get("/person/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId")).get
    personService.findByPersonId(personId)
  }

  get("/death/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId")).get
    deathService.findByPersonId(personId)
  }

  get("/condition-occurrence/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId")).get
    conditionOccurrenceService.findByPersonId(personId)
  }

  get("/observation/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId")).get
    observationService.findByPersonId(personId)
  }

  get("/observation-period/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId")).get
    observationPeriodService.findByPersonId(personId)
  }

}
