package controllers

import javax.inject.Inject

import play.api.mvc.{Action, Controller}
import services.{ReportService, UserService}

class Application @Inject()(reportService: ReportService, userService: UserService) extends Controller {

  def index = Action {
    Ok(reportService.foobar)
  }

  def users = Action {
    Ok(userService.findAll().mkString(", "))
  }

}
