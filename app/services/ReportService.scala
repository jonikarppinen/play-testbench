package services

import java.time.ZonedDateTime
import javax.inject.Inject

import play.api.Logger
import repositories.ReportRepository

class ReportService @Inject()(repository: ReportRepository, userService: UserService, supportService: SupportService) {

  private val log = Logger(this.getClass)

  def foobar = userService.foo + supportService.bar

  def findAll = repository.findAll()

  def count(customerId: Option[Long], createdSince: Option[ZonedDateTime]) = {
    log.info(s"Counting reports (customerId: $customerId, createdSince: $createdSince)")

    // Both of these now work
//    repository.countOption1(customerId, createdSince)
    repository.countOption2(customerId, createdSince)
  }

}

