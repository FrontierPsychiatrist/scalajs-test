import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{`Access-Control-Allow-Methods`, `Access-Control-Allow-Origin`}
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import tutorial.Person
import upickle.default.write

import scala.io.StdIn

object WebServer {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route =
      path("test") {
        get {
          complete(
            HttpResponse(StatusCodes.OK,
              List(`Access-Control-Allow-Methods`(HttpMethods.OPTIONS, HttpMethods.GET), `Access-Control-Allow-Origin`.*),
              HttpEntity(write(Person("Moritz")))
            ))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 5000)
    StdIn.readLine("Press enter to stop")
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}
