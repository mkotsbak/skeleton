package io.widok.client.pages

import io.widok.client.{Routes, DefaultHeader, CustomPage}
import org.widok.{View, InstantiatedRoute}

import org.widok._
import org.widok.html._

/**
 * Created by marius on 9/23/15.
 */
case class MainPage() extends CustomPage with DefaultHeader {
    override def body(route: InstantiatedRoute): View = {
        ul(
            li(
                a("User registry").url(Routes.userRegistry().uri())
            ),
            li(
                a("Dictionary").url(Routes.dictionary().uri())
            )
        )
    }
}
