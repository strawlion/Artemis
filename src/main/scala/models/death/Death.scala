package models.death

import org.joda.time.DateTime

case class Death (
   personId: Long,
   deathDate: DateTime,
   deathTypeConceptId: Long,
   causeConceptId: Option[Long],
   causeSourceValue: Option[String],
   causeSourceConceptId: Option[Long]
 )