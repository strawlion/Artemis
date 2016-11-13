package web

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import data.DataService


@Singleton
class DataController @Inject() (dataService: DataService) extends Controller {

  get("/") { request: Request =>
    "<h1>Hello, world!</h1>"
  }

  get("/data") { request: Request =>
    dataService.dataString
  }

}
