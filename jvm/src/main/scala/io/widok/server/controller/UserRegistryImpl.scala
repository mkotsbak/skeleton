package io.widok.server.controller

import io.widok.common.model.UserRegistry.User
import io.widok.common.protocol.UserRegistry

class UserRegistryImpl extends UserRegistry {
    final val testUser = User("Ola", "Nordmann")
    override def createUser(newUser: User): User = {
        testUser
    }

    override def lookupFirstNamePart(firstname: String): Seq[User] = {
        Seq(testUser)
    }
}
