package models.procedureoccurrence

import com.google.inject.Singleton
import models.DataService
import models.person.Person
import org.apache.spark.sql.Row
import org.joda.time.DateTime
import utils.{PartitionUtils, CsvUtils, NumberUtils}

@Singleton
class ProcedureOccurrenceService {

  def findByPersonId(personId: Long): Array[ProcedureOccurrence] = {
    procedureOccurrence.filter("partition_id == " + PartitionUtils.getPartitionId(personId) + " AND person_id == " + personId)
                      .collect
                      .map(toProcedureOccurrence)
  }

  private val procedureOccurrence = CsvUtils.writeParquetFromCsv(DataService.spark, "data/procedure_occurrence.csv", "data/parquet/procedure_occurrence.parquet", false)

  private val toProcedureOccurrence = (row: Row) => ProcedureOccurrence(
      NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
      row.get(1).asInstanceOf[Long],
      NumberUtils.toLong(row.get(2).asInstanceOf[String]).get,
      DateTime.parse(row.get(3).asInstanceOf[String]),
      NumberUtils.toLong(row.get(4).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(5).asInstanceOf[String]),
      NumberUtils.toLong(row.get(6).asInstanceOf[String]),
      NumberUtils.toLong(row.get(7).asInstanceOf[String]),
      NumberUtils.toLong(row.get(8).asInstanceOf[String]),
      Option(row.get(9).asInstanceOf[String]),
      NumberUtils.toLong(row.get(10).asInstanceOf[String]),
      Option(row.get(11).asInstanceOf[String])
    )
}
