package scheduler

import javax.inject.{Inject, Singleton}

import akka.actor.Actor
import play.api.Logger
import services.UserService


// Here, injecting and using UserService works just fine
@Singleton
class SchedulerActor @Inject()(userService: UserService) extends Actor {

  val log = Logger(this.getClass)
  log.info("Initializing SchedulerActor...")

  def receive = {
    case "cleanup" => cleanup()
  }

  def cleanup(): Unit = {
    userService.cleanupTokens()
    log.info("Users: " + userService.findAll())
  }

}