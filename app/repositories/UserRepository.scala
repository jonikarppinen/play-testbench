package repositories


class UserRepository {

  // Dummy implementation.
  // In reality, this would implement e.g. access to SQL database using Anorm
  def findAll = {
    List("Joe", "Mary")
  }

}
