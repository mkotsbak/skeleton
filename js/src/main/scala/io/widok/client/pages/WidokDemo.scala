package io.widok.client.pages

import io.widok.client.{CustomPage, DefaultHeader}
import org.widok._
import org.widok.bindings.HTML.{ButtonType, Button}
import org.widok.html._
import pl.metastack.metarx.Var

case class WidokDemo() extends CustomPage with DefaultHeader {
    val name = Var("")
    val hasName = name.map(_.nonEmpty)

    override def body(route: InstantiatedRoute): View = div(
        h1("Welcome to Widok!"),
        p("Please enter your name:"),

        text().bind(name),

        p("Hello, ", name).show(hasName),

        button("Change my name").show(name.isNot("tux")).onClick(_ => name := "tux"),
        button("Log out").show(hasName).onClick(_ => name := "")
    )
}
