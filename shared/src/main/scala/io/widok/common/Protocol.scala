package io.widok.common

trait Protocol {
  val dictionary: protocol.Dictionary
  val userRegistry: protocol.UserRegistry
}

object Protocol {
  val Namespace = Seq("io", "widok", "common", "Protocol")
}