package web

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import data.DataService
import data.observation.{ObservationRepo, Observation}
import data.person.{Person, PersonRepo}
import org.apache.spark.sql.Dataset
import org.apache.spark.sql._
import utils.NumberUtils

@Singleton
class DataController @Inject() (personRepo: PersonRepo,
                                observationRepo: ObservationRepo) extends Controller {

  get("/") { request: Request =>
    "<h1>Hello, world!</h1>"
  }
//
//  get("/data") { request: Request =>
//    personRepo.person(0)
//  }
//
//  get("/data2") { request: Request =>
//    observationRepo.observation(0)
//  }

  get("/persons") { request: Request =>
    personRepo.person.take(20).map(personRepo.toPerson)
  }

  get("/person/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId"))
    personRepo.person.filter("person_id == " + personId.get).collect.map(personRepo.toPerson).last
  }

  get("/observations/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId"))
    observationRepo.observation.filter("person_id == " + personId.get).collect.map(observationRepo.toObservation)
  }

  get("/observation-periods/:personId") { request: Request =>
    val personId = NumberUtils.toLong(request.params("personId"))
    observationRepo.observation.filter("person_id == " + personId.get).collect.map(observationRepo.toObservation)
  }

}
