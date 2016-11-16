package models.person

import com.google.inject.{Inject, Singleton}
import models.DataService
import org.apache.spark.sql._
import utils.{CsvUtils, NumberUtils}


@Singleton
class PersonRepo extends java.io.Serializable {
  import DataService.spark.implicits._
  val person = CsvUtils.getDataframe(DataService.spark, "models/person.csv").cache
//  val person = personFast.rdd.map(toPerson)


  def toPerson(row: Row): Person = {
    return Person(
      NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(1).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(2).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(3).asInstanceOf[String]),
      NumberUtils.toLong(row.get(4).asInstanceOf[String]),
      Option(row.get(5).asInstanceOf[String]),
      NumberUtils.toLong(row.get(6).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(7).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(8).asInstanceOf[String]),
      NumberUtils.toLong(row.get(9).asInstanceOf[String]),
      NumberUtils.toLong(row.get(10).asInstanceOf[String]),
      Option(row.get(11).asInstanceOf[String]),
      Option(row.get(12).asInstanceOf[String]),
      NumberUtils.toLong(row.get(13).asInstanceOf[String]),
      Option(row.get(14).asInstanceOf[String]),
      NumberUtils.toLong(row.get(15).asInstanceOf[String]),
      Option(row.get(16).asInstanceOf[String]),
      NumberUtils.toLong(row.get(17).asInstanceOf[String])
    )
  }

}
