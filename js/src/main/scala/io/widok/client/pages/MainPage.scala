package io.widok.client.pages

import io.widok.client.{Routes, DefaultHeader, CustomPage}
import org.widok.{View, InstantiatedRoute}

import org.widok._
import org.widok.html._

/**
 * Created by marius on 9/23/15.
 */
case class MainPage() extends CustomPage with DefaultHeader {
    val entries = Map(
        "Widok demo" -> Routes.widokDemo(),
        "User registry" -> Routes.userRegistry(),
        "Dictionary" -> Routes.dictionary()
    )

    override def body(route: InstantiatedRoute): View = {
        ul(
            entries.map { case (name, route) =>
                li(
                    a(name).url(route.uri())
                )
            }.toSeq :_*
        )
    }
}
