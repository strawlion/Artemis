package web

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import models.DataService
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
                               observationPeriodService: ObservationPeriodService) extends Controller {

  get("/") { request: Request =>
    "<h1>Hello, world!</h1>"
  }

  get("/person/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId")).get
    personService.findByPersonId(personId)
  }

  get("/death/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId")).get
    deathService.findByPersonId(personId)
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
