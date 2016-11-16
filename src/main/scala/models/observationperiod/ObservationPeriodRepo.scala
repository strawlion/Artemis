package models.observationperiod

import com.google.inject.{Inject, Singleton}
import models.DataService
import models.observation.Observation
import org.apache.spark.sql.Row
import org.joda.time.DateTime
import utils.{NumberUtils, CsvUtils}

@Singleton
class ObservationPeriodRepo extends java.io.Serializable  {

  val observationPeriod = CsvUtils.getDataframe(DataService.spark, "models/observation_period.csv").cache
//  val observationPeriod = observationPeriodFast.rdd.map(toObservationPeriod)

  def toObservationPeriod(row: Row): ObservationPeriod = {
    return ObservationPeriod(
      NumberUtils.toLong(row.get(0).asInstanceOf[String]).get,
      NumberUtils.toLong(row.get(1).asInstanceOf[String]).get,
      DateTime.parse(row.get(2).asInstanceOf[String]),
      DateTime.parse(row.get(3).asInstanceOf[String]),
      NumberUtils.toLong(row.get(4).asInstanceOf[String]).get
    )
  }
}
