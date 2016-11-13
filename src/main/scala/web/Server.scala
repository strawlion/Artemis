package web

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.filters.CommonFilters
import com.twitter.finatra.http.routing.HttpRouter
import org.apache.spark.{SparkConf, SparkContext}

import scala.io.Source

object ServerMain extends Server

class Server extends HttpServer {

  override def configureHttp(router: HttpRouter): Unit = {
    router
      .filter[CommonFilters]
      .add[DataController]
  }
}