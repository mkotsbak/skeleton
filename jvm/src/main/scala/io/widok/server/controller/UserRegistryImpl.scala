package io.widok.server.controller

import io.widok.common.model.UserRegistry.User
import io.widok.common.protocol.UserRegistry

class UserRegistryImpl extends UserRegistry {
    final val testUser  = User("Ola", "Nordmann", lat = 63.43F, lon = 10.40F)
    final val testUser2 = User("Kari", "Nordkvinne", lat = 62.20F, lon = 09.30F)

    override def createUser(newUser: User): User = {
        testUser
    }

    override def lookupFirstNamePart(firstname: String): Seq[User] = {
        Seq(testUser, testUser2)
    }
}
