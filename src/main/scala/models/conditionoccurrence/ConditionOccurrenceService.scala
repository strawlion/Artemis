package models.conditionoccurrence

import models.DataService
import models.death.Death
import org.apache.spark.sql.Row
import org.joda.time.DateTime
import utils.{PartitionUtils, NumberUtils, CsvUtils}


class ConditionOccurrenceService {
  def findByPersonId(personId: Long): ConditionOccurrence = {
    val conditionOccurrences = conditionOccurrence.filter("partition_id == " + PartitionUtils.getPartitionId(personId) + " AND person_id == " + personId)
                                                .collect
                                                .map(toConditionOccurrence)
    return if (conditionOccurrences.nonEmpty) conditionOccurrences.head else null
  }

  private val conditionOccurrence = CsvUtils.writeParquetFromCsv(DataService.spark, "data/condition_occurrence.csv", "data/parquet/condition_occurrence.parquet", false)

  private val toConditionOccurrence = (row: Row) => ConditionOccurrence(
    NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
    NumberUtils.toLong(row.get(1).asInstanceOf[String]).get,
    NumberUtils.toLong(row.get(2).asInstanceOf[String]).get,
    DateTime.parse(row.get(3).asInstanceOf[String]),
    Option(DateTime.parse(row.get(4).asInstanceOf[String])),
    NumberUtils.toLong(row.get(5).asInstanceOf[String]).get,
    Option(row.get(6).asInstanceOf[String]),
    NumberUtils.toLong(row.get(7).asInstanceOf[String]),
    NumberUtils.toLong(row.get(8).asInstanceOf[String]),
    Option(row.get(9).asInstanceOf[String]),
    NumberUtils.toLong(row.get(10).asInstanceOf[String])
  )
}
