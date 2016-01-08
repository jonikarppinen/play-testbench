package scheduler

import javax.inject.{Inject, Singleton}

import akka.actor.Actor
import play.api.Logger


// TODO: how to get UserService injected here?
@Singleton
class SchedulerActor @Inject() extends Actor {

  val log = Logger(this.getClass)
  log.info("Initializing SchedulerActor...")

  def receive = {
    case "cleanup" => cleanup()
  }

  def cleanup(): Unit = {
    log.info("cleanup running")
    // TODO: How to call UserService.cleanupTokens() from here?
  }


}