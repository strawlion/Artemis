package models.observationperiod

import org.joda.time.DateTime

case class ObservationPeriod (
   observationPeriodId: Long,
   personId: Long,
   observationPeriodStartDate: DateTime,
   observationPeriodEndDate: DateTime,
   periodTypeConceptId: Long
 )
