package repositories

import java.time.ZonedDateTime

import anorm.SqlParser._
import anorm._
import models.Report
import play.api.Logger
import play.api.Play.current
import play.api.db.DB

import scala.util.{Failure, Success, Try}

/**
 * Test case for http://stackoverflow.com/questions/34078955/anorm-where-condition-conditionally
 */
class ReportRepository {

  private val log = Logger(this.getClass)

  private def parser = (
    get[Long]("id") ~
      get[Long]("customer_id") ~
      get[String]("name") ~
      get[ZonedDateTime]("created")
    ).map(flatten)

  private def rowToObject(row: (Long, Long, String, ZonedDateTime)): Option[Report] =
    Try(Report(row._1, row._2, row._3, row._4)) match {
      case Failure(e) =>
        log.error(e.getMessage, e)
        None
      case Success(report) =>
        Some(report)
    }

  def findAll() =
    DB.withConnection {
      implicit c =>
        SQL"SELECT * FROM report"
          .as(parser.*)
          .flatMap(rowToObject)
    }

  /**
   * Using the approach posted by wwkudu at http://stackoverflow.com/a/34081826/56285
   * This works fine.
   */
  def countOption1(customerId: Option[Long], createdSince: Option[ZonedDateTime]) = {
    val customerClause =
      if (customerId.isEmpty) ""
      else " and customer_id = {customerId}"
    val createdClause =
      if (createdSince.isEmpty) ""
      else " and created >= {created}"

    DB.withConnection {
      implicit c =>
        SQL( s"""
             SELECT count(*) FROM report
             WHERE true $customerClause $createdClause
             """)
          .on('customerId -> customerId, 'created -> createdSince)
          .as(scalar[Int].singleOpt).getOrElse(0)
    }
  }

  /**
   * Applying the approach by Joel Arnold at http://stackoverflow.com/a/18308023/56285
   *
   * This now works fine as well! As Joel pointed out, with ZonedDateTime we need to use
   * {created}::timestamptz in the null check, as a workaround to a PostgreSQL driver issue.
   */
  def countOption2(customerId: Option[Long], createdSince: Option[ZonedDateTime]) =
    DB.withConnection {
      implicit c =>
        SQL( """
            SELECT count(*) FROM report
            WHERE ({customerId} is null or customer_id = {customerId})
            AND ({created}::timestamptz is null or created >= {created})
             """)
          .on('customerId -> customerId, 'created -> createdSince)
          .as(scalar[Int].singleOpt).getOrElse(0)
    }

}
