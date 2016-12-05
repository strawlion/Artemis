package models.death

import com.google.inject.{Inject, Singleton}
import models.DataService
import org.apache.spark.sql.Row
import org.joda.time.DateTime
import utils.{PartitionUtils, CsvUtils, NumberUtils}


@Singleton
class DeathService {

  def findByPersonId(personId: Long): Death = {
    val deaths = death.filter("partition_id == " + PartitionUtils.getPartitionId(personId) + " AND person_id == " + personId)
                      .collect
                      .map(toDeath)
    return if (deaths.nonEmpty) deaths.head else null
  }

  private val death = CsvUtils.writeParquetFromCsv(DataService.spark, "data/death.csv", "data/parquet/death.parquet", false)

  private val toDeath = (row: Row) => Death(
      row.get(0).asInstanceOf[Long],
      DateTime.parse(row.get(1).asInstanceOf[String]),
      NumberUtils.toLong(row.get(2).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(3).asInstanceOf[String]),
      Option(row.get(4).asInstanceOf[String]),
      NumberUtils.toLong(row.get(5).asInstanceOf[String])
    )
}
