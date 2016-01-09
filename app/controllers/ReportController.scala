package controllers

import java.time.ZonedDateTime
import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ReportService
import utils.DateUtils.toUtcDateTime

import scala.language.implicitConversions

class ReportController @Inject()(service: ReportService) extends Controller {

  def all() = Action {
    Ok(Json.toJson(Json.obj("reports" -> service.findAll)))
  }

  def count(customerId: Option[Long], createdSince: Option[Long]) = Action {
    Ok(Json.toJson(Json.obj("count" -> service.count(customerId, createdSince))))
  }

  implicit private def toDateTime(epochMilli: Option[Long]): Option[ZonedDateTime] = epochMilli.map(toUtcDateTime)

}
