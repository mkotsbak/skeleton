package io.widok.client

import org.widok._

object Routes {
    val mainPage = Route("/", pages.MainPage)
    val dictionary = Route("/dict", pages.Dictionary)
    val dictionaryLookUp = Route("/dict/:word", pages.Dictionary)
    val userRegistry = Route("/userReg", pages.UserRegistry)
    val widokDemo = Route("/widokDemo", pages.WidokDemo)
    val notFound = Route("/404", pages.NotFound)

    val routes = Set(
        mainPage, userRegistry, dictionary, dictionaryLookUp, widokDemo, notFound
    )
}

object Application extends RoutingApplication(Routes.routes, Routes.notFound) {
  val preload = ServerOps.Dictionary.timeout(5000)
}
