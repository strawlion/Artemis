package data.observation

import org.joda.time.DateTime

case class Observation (
     observationId: Long,
     personId: Long,
     observationConceptId: Long,
     observationDate: DateTime, // TODO: Use date type
     observationTime: Long, // TODO: Use time type
     observationTypeConceptId: Long,
     valueAsNumber: Option[Float],
     valueAsString: Option[String],
     valueAsConceptId: Option[Long],
     qualifierConceptId: Option[Long],
     unitConceptId: Option[Long],
     providerId: Option[Long],
     visit_occurrenceId: Option[Long],
     observationSourceValue: Option[String],
     observationSourceConceptId: Option[Long],
     unitSourceValue: Option[String],
     qualifierSourceValue: Option[String]
  )
