package models.observation

import com.google.inject.{Inject, Singleton}
import models.DataService
import org.apache.spark.sql._
import org.joda.time.DateTime
import utils.{PartitionUtils, CsvUtils, NumberUtils}

@Singleton
class ObservationService {

  def findByPersonId(personId: Long): Array[Observation] = {
      observation.filter("partition_id == " + PartitionUtils.getPartitionId(personId) + " AND person_id == " + personId)
                  .collect
                  .map(toObservation)
  }

  private val observation = CsvUtils.writeParquetFromCsv(DataService.spark, "data/observation.csv", "data/parquet/observation.parquet", false)

  private val toObservation = (row: Row) => Observation(
      NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
      row.get(1).asInstanceOf[Long],
      NumberUtils.toLong(row.get(2).asInstanceOf[String]).get,
      DateTime.parse(row.get(3).asInstanceOf[String]),
      DateTime.parse(row.get(3).asInstanceOf[String]).getMillis,
      NumberUtils.toLong(row.get(5).asInstanceOf[String]).get,
      NumberUtils.toFloat(row.get(6).asInstanceOf[String]),
      Option(row.get(7).asInstanceOf[String]),
      NumberUtils.toLong(row.get(8).asInstanceOf[String]),
      NumberUtils.toLong(row.get(9).asInstanceOf[String]),
      NumberUtils.toLong(row.get(10).asInstanceOf[String]),
      NumberUtils.toLong(row.get(11).asInstanceOf[String]),
      NumberUtils.toLong(row.get(12).asInstanceOf[String]),
      Option(row.get(13).asInstanceOf[String]),
      NumberUtils.toLong(row.get(14).asInstanceOf[String]),
      Option(row.get(15).asInstanceOf[String]),
      Option(row.get(16).asInstanceOf[String])
    )


}
