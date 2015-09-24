package io.widok.client.pages

import io.widok.client.{Server, DefaultHeader, CustomPage}
import io.widok.common.Protocol
import io.widok.common.model.UserRegistry.User
import org.scalajs.dom.ext.Color
import org.widok.bindings.HTML.Container
import org.widok.{View, InstantiatedRoute}

import scala.concurrent.ExecutionContext.Implicits.global

import org.widok._
import org.widok.html._

import autowire._ // for macros
import pl.metastack.metarx.{Opt, Var, Buffer, ReadBuffer}


/**
 * Created by marius on 9/23/15.
 */
case class UserRegistry() extends CustomPage with DefaultHeader {
    val selectedUser = Opt[User]()

    // Like React/Angular component
    def userRow(user: User) = {
        tr(
            td(user.firstName).hover(Color.Blue),
            td(user.lastName).hover(Color.Cyan),
            button("Select").onClick( _ => selectedUser := user)
        )
    }

    // Simulate Angular directive
    implicit class widgetEx(orig: Widget[_]) {
        def hover(color: Color) = {
            val bgColor = Opt[String]()

            orig.attributeOpt("bgcolor", bgColor)
            orig.onMouseOut(_ => bgColor.clear())
            orig.onMouseOver(_ => bgColor := color.toString())
        }
    }

    override def body(route: InstantiatedRoute): View = {
        val users: ReadBuffer[User] = Buffer.from(
            Server[Protocol].userRegistry.lookupFirstNamePart("Test").call()
        )

        div(
            table(
                thead(
                    tr(
                        td("First name").hover(Color.Yellow),
                        td("Last name").hover(Color.Green)
                    )
                ),
                tbody(
                        users map userRow
                )
            ),

            div(
                p("User details"),
                p("User", selectedUser.mapOrElse(_.toString, "None"))
            )
        )
    }
}
