package io.widok.client.pages

import chandu0101.scalajs.react.components.fascades.{LatLng, Marker}
import chandu0101.scalajs.react.components.maps.GoogleMap
import io.widok.client.{Server, DefaultHeader, CustomPage}
import io.widok.common.Protocol
import io.widok.common.model.UserRegistry.User
import org.scalajs.dom.ext.Color
import org.widok.bindings.scalajsReact.ReactComponentWrapper.ReactDynamic
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

    val searchQuery = Var("")

    val defaultLatLng = LatLng(63, 10)
    val latlng = selectedUser.mapOrElse(user => LatLng(user.lat, user.lon), defaultLatLng)

    final val markers = List(
        Marker( position = LatLng(-33.890542,151.274856) ,title = "Bondi Beach" )
    )

    def googleMapProps(width: String = "500px" , height: String = "500px", center: LatLng, zoom: Int = 4, markers: List[Marker] = Nil,url : String = "https://maps.googleapis.com/maps/api/js") = GoogleMap.Props(width,height,center, zoom, markers,url)

    val mapProps = latlng.map( curPos => googleMapProps(center = curPos, zoom = 10, markers = markers))

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
        val users: ReadBuffer[User] = searchQuery.distinct.flatMapBuf { query => Buffer.from(
            Server[Protocol].userRegistry.lookupFirstNamePart(query).call()
            )
        }

        div(
            p("First name: ", text().bind(searchQuery)),
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
                p("User: ", selectedUser.mapOrElse(_.toString, "None")),
                ReactDynamic(GoogleMap.component, mapProps)
            )
        )
    }
}
