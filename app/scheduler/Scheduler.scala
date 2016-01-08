package scheduler

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}
import play.api.{Application, Logger}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._
import scala.language.postfixOps

class Scheduler @Inject()(val app: Application,
                          val system: ActorSystem,
                          @Named("scheduler-actor") val schedulerActor: ActorRef)
                         (implicit ec: ExecutionContext) {

  val log = Logger(this.getClass)
  log.info("Initializing Scheduler...")

  implicit val application = app

  system.scheduler.schedule(0 seconds, 15 seconds, schedulerActor, "cleanup")

}