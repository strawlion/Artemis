package models.person

import java.nio.file.{Paths, Files}

import com.google.inject.{Inject, Singleton}
import models.DataService
import org.apache.spark.sql._
import org.joda.time.DateTime
import utils.{PartitionUtils, CsvUtils, NumberUtils}


@Singleton
class PersonService extends java.io.Serializable {

  def findByPersonId(personId: Long): Person = {

    val persons = person.filter("partition_id == " + PartitionUtils.getPartitionId(personId) + " AND person_id == " + personId)
                        .collect
                        .map(toPerson)
    return if (persons.nonEmpty) persons.head else null
  }

  def findWithLimit(limit: Int): Array[Person] = {
    return person.limit(limit).collect.map(toPerson)
  }

  def findByGenderIdWithLimit(genderId: Long, limit: Int): Array[Person] = {
    return person.filter("gender_concept_id == " + genderId) .limit(limit).collect.map(toPerson)
  }

  def findByFilters(filters: Array[String]): Array[Person] = {
    if (filters.nonEmpty) {
      return person.filter(filters.mkString(" AND ")).limit(20).collect.map(toPerson)
    }

    person.limit(20).collect.map(toPerson)
  }


  private val person = getPerson()

  private def getPerson(): Dataset[Row] = {
    val parquetPath = "data/parquet/person.parquet"
    val shouldWriteFile = !Files.exists(Paths.get(parquetPath))

    if (shouldWriteFile) {
      val death = CsvUtils.getDataframe(DataService.spark, "data/death.csv").select("person_id", "death_date")
      val person = CsvUtils.getDataframe(DataService.spark, "data/person.csv")
      val joinedPerson = person.join(death, Seq("person_id"), "left_outer")
      return CsvUtils.writeParquetFromDataFrame(DataService.spark, joinedPerson, parquetPath, false)
    }

    DataService.spark.read
      .parquet(parquetPath)
  }
  private val toPerson = (row: Row) => Person(
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
      NumberUtils.toLong(row.get(17).asInstanceOf[String]),
      Option( if (row.get(18) == null) null else DateTime.parse(row.get(18).asInstanceOf[String]))
    )

}
