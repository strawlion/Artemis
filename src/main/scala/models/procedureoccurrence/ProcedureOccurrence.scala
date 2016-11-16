package models.procedureoccurrence

import org.joda.time.DateTime


case class ProcedureOccurrence(
  procedureOccurrenceId: Long,
  personId: Long,
  procedureConceptId: Long,
  procedureDate: DateTime,
  procedure_typeConceptId: Long,
  modifierConceptId: Option[Long],
  quantity: Option[Long],
  providerId: Option[Long],
  visitOccurrenceId: Option[Long],
  procedureSource_value: Option[String],
  procedureSourceConceptId: Option[Long],
  qualifierSource_value: Option[String]
)
