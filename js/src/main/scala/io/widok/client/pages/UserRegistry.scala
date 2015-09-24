package io.widok.client.pages

import io.widok.client.{Server, DefaultHeader, CustomPage}
import io.widok.common.Protocol
import io.widok.common.model.UserRegistry.User
import org.widok.{View, InstantiatedRoute}

import scala.concurrent.ExecutionContext.Implicits.global

import org.widok._
import org.widok.html._

import autowire._
import pl.metastack.metarx.{Buffer, ReadBuffer, ReadChannel, Channel}

// for macros

/**
 * Created by marius on 9/23/15.
 */
case class UserRegistry() extends CustomPage with DefaultHeader {

    def userRow(user: User) = {
        tr(
            td(user.firstName),
            td(user.lastName)
        )
    }

    override def body(route: InstantiatedRoute): View = {
        val users: ReadBuffer[User] = Buffer.from(
            Server[Protocol].userRegistry.lookupFirstNamePart("Test").call()
        )

        table(
            thead(
                tr(
                    td("First name"), td("Last name")
                )
            ),
            tbody(
                users map userRow
            )
        )
    }
}
