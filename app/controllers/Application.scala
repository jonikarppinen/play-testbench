package controllers

import javax.inject.Inject

import models.FooJson
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import services.ReportService

class Application @Inject()(reportService: ReportService) extends Controller {

  def index = Action {
    Ok(reportService.foobar)
  }

}
