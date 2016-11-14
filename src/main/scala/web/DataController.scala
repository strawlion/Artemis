package web

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import data.DataService
import data.SignalRef.SignalRefRepo
import data.person.{Person, PersonRepo}
import org.apache.spark.sql.Dataset


@Singleton
class DataController @Inject() (dataService: DataService) extends Controller {
  import dataService.spark.implicits._

  val person: Dataset[Person] = dataService.spark.read
    .format("csv")
    .option("header", "true")
    .option("nullValue", "")
    .csv("data/person.csv")
    .as[Person]

  get("/") { request: Request =>
    "<h1>Hello, world!</h1>"
  }

  get("/data") { request: Request =>
    person.toJSON.collect()
  }

  get("/gender") { request: Request =>
    val count = person.groupByKey(_.gender_concept_id).count()
    count
  }

}
