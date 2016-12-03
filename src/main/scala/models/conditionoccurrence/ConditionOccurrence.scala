package models.conditionoccurrence

import org.joda.time.DateTime

case class ConditionOccurrence (
     conditionOccurrenceId: Long,
     personId: Long,
     conditionConceptId: Long,
     conditionStartDate: DateTime,
     conditionEndDate: Option[DateTime],
     conditionTypeConceptId: Long,
     stopReason: Option[String],
     providerId: Option[Long],
     visitOccurrenceId: Option[Long],
     conditionSourceValue: Option[String],
     conditionSourceConceptId: Option[Long]
 )