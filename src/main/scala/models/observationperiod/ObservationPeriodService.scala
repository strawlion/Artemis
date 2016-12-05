package models.observationperiod

import com.google.inject.{Inject, Singleton}
import models.DataService
import models.observation.Observation
import org.apache.spark.sql.Row
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormatter
import org.joda.time.format.DateTimeFormat
import utils.{PartitionUtils, NumberUtils, CsvUtils}

@Singleton
class ObservationPeriodService {

  def findByPersonId(personId: Long): Array[ObservationPeriod] = {
    observationPeriod.filter("partition_id == " + PartitionUtils.getPartitionId(personId) + " AND person_id == " + personId)
                    .collect
                    .map(toObservationPeriod)
  }

  private val observationPeriod = CsvUtils.writeParquetFromCsv(DataService.spark, "data/observation_period.csv", "data/parquet/observation_period.parquet", false)
  private val dateFormatter = DateTimeFormat.forPattern("yyyyMMDD")
  private val toObservationPeriod = (row: Row) => ObservationPeriod(
      NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
      row.get(1).asInstanceOf[Long],
      DateTime.parse(row.get(2).asInstanceOf[String], dateFormatter),
      DateTime.parse(row.get(2).asInstanceOf[String], dateFormatter).getMillis,
      DateTime.parse(row.get(3).asInstanceOf[String], dateFormatter),
      DateTime.parse(row.get(3).asInstanceOf[String], dateFormatter).getMillis,
      NumberUtils.toLong(row.get(4).asInstanceOf[String]).get
    )
}
