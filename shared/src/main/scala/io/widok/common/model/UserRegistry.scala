package io.widok.common.model

/**
 * Created by marius on 9/23/15.
 */
object UserRegistry {
    case class User(firstName: String, lastName: String,
                   lat: Float, lon: Float)
}
