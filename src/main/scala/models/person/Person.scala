package data.person

case class Person(
  personId: Long,
  genderConceptId: Long,
  yearOfBirth: Long,
  monthOfBirth: Option[Long],
  dayOfBirth: Option[Long],
  timeOfBirth: Option[String], // TODO: Use actual timestamp type
  raceConceptId: Long,
  ethnicityConceptId: Long,
  locationId: Option[Long],
  providerId: Option[Long],
  careSiteId: Option[Long],
  personSourceValue: Option[String],
  genderSourceValue: Option[String],
  genderSourceConceptId: Option[Long],
  raceSourceValue: Option[String],
  raceSourceConceptId: Option[Long],
  ethnicitySourceValue: Option[String],
  ethnicitySourceConceptId: Option[Long]
)

