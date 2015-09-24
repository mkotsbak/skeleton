package io.widok.common.protocol

import io.widok.common._

sealed trait Response[+T]
object Response {
  case class Failure(error: String) extends Response[Nothing]
  case class Success[+T](value: T) extends Response[T]
}

sealed trait EmptyResponse
object EmptyResponse {
  case class Failure(error: String) extends EmptyResponse
  case class Success() extends EmptyResponse
}

trait Dictionary {
  def timeout(ms: Int): Response[String]
  def lookUp(headword: String): Response[model.Dictionary.Result]
}
