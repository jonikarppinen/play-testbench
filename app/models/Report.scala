package models

import java.time.ZonedDateTime

import play.api.libs.json.Json

/**
 * Dummy model object, related to
 * http://stackoverflow.com/questions/34078955/anorm-where-condition-conditionally
 */
case class Report(id: Long, customerId: Long, name: String, created: ZonedDateTime)

object Report {
  implicit val format = Json.format[Report]
}