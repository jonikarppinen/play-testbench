package scheduler

import com.google.inject.AbstractModule
import play.api.Logger
import play.api.libs.concurrent.AkkaGuiceSupport


class SchedulerModule extends AbstractModule with AkkaGuiceSupport {
  val log = Logger(this.getClass)
  log.info("Initializing SchedulerModule...")

  def configure() = {
    bindActor[SchedulerActor]("scheduler-actor")
    bind(classOf[Scheduler]).asEagerSingleton()
  }

}