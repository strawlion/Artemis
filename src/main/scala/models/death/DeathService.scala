package models.death

import com.google.inject.{Inject, Singleton}
import models.DataService
import org.apache.spark.sql.Row
import org.joda.time.DateTime
import utils.{CsvUtils, NumberUtils}


@Singleton
class DeathService {

  def findByPersonId(personId: Long): Death = {
    val deaths = death.filter("person_id == " + personId).collect.map(toDeath)
    return if (deaths.nonEmpty) deaths.head else null
  }

  private val death = CsvUtils.writeParquetFromCsv(DataService.spark, "data/death.csv", "data/parquet/death.parquet", false).cache

  private val toDeath = (row: Row) => Death(
      NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
      DateTime.parse(row.get(1).asInstanceOf[String]),
      NumberUtils.toLong(row.get(2).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(3).asInstanceOf[String]),
      Option(row.get(4).asInstanceOf[String]),
      NumberUtils.toLong(row.get(5).asInstanceOf[String])
    )
}
