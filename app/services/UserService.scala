package services

import javax.inject.Inject

import play.api.Logger
import repositories.UserRepository

class UserService @Inject()(repository: UserRepository) {
  val log = Logger(this.getClass)

  def foo = "foo"

  def findAll() = {
    log.info("Finding all users")
    repository.findAll
  }

  def cleanupTokens() = {
    log.info("cleanupTokens triggered")
    // ...
  }

}
