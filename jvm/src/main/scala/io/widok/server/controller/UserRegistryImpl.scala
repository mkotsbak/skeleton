package io.widok.server.controller

import io.widok.common.model.UserRegistry.User
import io.widok.common.protocol.UserRegistry

import scala.collection.mutable

class UserRegistryImpl extends UserRegistry {
    final val testUser  = User("Ola", "Nordmann", lat = 63.43F, lon = 10.40F)
    final val testUser2 = User("Kari", "Nordkvinne", lat = 62.20F, lon = 09.30F)

    val users = mutable.MutableList(testUser, testUser2)

    override def createUser(newUser: User): User = {
        users += newUser
        newUser
    }

    override def lookupFirstNamePart(firstnamePart: String): Seq[User] = {
        users.filter(_.firstName.startsWith(firstnamePart))
    }
}
