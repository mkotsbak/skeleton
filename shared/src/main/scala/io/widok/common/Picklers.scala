package io.widok.common

/**
 * Created by marius on 9/23/15.
 */
object Picklers {
  import upickle.Aliases._
  import upickle.Js

  implicit def FailureR: R[Response.Failure] = R[Response.Failure] {
    case Js.Arr(Js.Num(0), x) => Response.Failure(upickle.readJs[String](x))
  }

  implicit def SuccessR[T: R]: R[Response.Success[T]] = R[Response.Success[T]] {
    case Js.Arr(Js.Num(1), x) => Response.Success(upickle.readJs[T](x))
  }

  implicit def ResponseR[T: R]: R[Response[T]] = R[Response[T]](
    SuccessR[T].read orElse FailureR.read
  )

  implicit def FailureW[T: W]: W[Response.Failure] = W[Response.Failure](ResponseW[T].write)

  implicit def SuccessW[T: W]: W[Response.Success[T]] = W[Response.Success[T]](ResponseW[T].write)

  implicit def ResponseW[T: W]: W[Response[T]] = W[Response[T]] {
    case Response.Failure(s) => Js.Arr(Js.Num(0), upickle.writeJs(s))
    case Response.Success(t) => Js.Arr(Js.Num(1), upickle.writeJs(t))
  }

  implicit def FailureR2: R[EmptyResponse.Failure] = R[EmptyResponse.Failure] {
    case Js.Arr(Js.Num(0), x) => EmptyResponse.Failure(upickle.readJs[String](x))
  }

  implicit def SuccessR2: R[EmptyResponse.Success] = R[EmptyResponse.Success] {
    case Js.Arr(Js.Num(1)) => EmptyResponse.Success()
  }

  implicit def ResponseR2: R[EmptyResponse] = R[EmptyResponse](
    SuccessR2.read orElse FailureR2.read
  )

  implicit def FailureW2: W[EmptyResponse.Failure] = W[EmptyResponse.Failure](ResponseW2.write)

  implicit def SuccessW2: W[EmptyResponse.Success] = W[EmptyResponse.Success](ResponseW2.write)

  implicit def ResponseW2: W[EmptyResponse] = W[EmptyResponse] {
    case EmptyResponse.Failure(s) => Js.Arr(Js.Num(0), upickle.writeJs(s))
    case EmptyResponse.Success() => Js.Arr(Js.Num(1))
  }
}
