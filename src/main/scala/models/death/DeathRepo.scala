package data.death

import com.google.inject.{Inject, Singleton}
import data.DataService
import org.apache.spark.sql.Row
import org.joda.time.DateTime
import utils.{CsvUtils, NumberUtils}


@Singleton
class DeathRepo extends java.io.Serializable  {

  val death = CsvUtils.getDataframe(DataService.spark, "data/death.csv").cache

  def toDeath(row: Row): Death = {

    return Death(
      NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
      DateTime.parse(row.get(1).asInstanceOf[String]),
      NumberUtils.toLong(row.get(2).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(3).asInstanceOf[String]),
      Option(row.get(4).asInstanceOf[String]),
      NumberUtils.toLong(row.get(5).asInstanceOf[String])
    )
  }
}
