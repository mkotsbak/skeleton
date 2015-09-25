package io.widok.common.protocol

import io.widok.common.model.UserRegistry.User

trait UserRegistry {
  def createUser(newUser: User): User
  def lookupFirstNamePart(firstname: String, exact: Boolean): Seq[User]
}
