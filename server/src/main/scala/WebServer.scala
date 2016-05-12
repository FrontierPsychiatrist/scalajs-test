import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.io.StdIn

object WebServer {

  def main(args: Array[String]): Unit = {
    implicit val system = ActorSystem()
    implicit val materializer = ActorMaterializer()
    implicit val executionContext = system.dispatcher

    val route =
      path("test") {
        get {
          complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "Hello World"))
        }
      }

    val bindingFuture = Http().bindAndHandle(route, "localhost", 5000)
    StdIn.readLine("Press enter to stop")
    bindingFuture
      .flatMap(_.unbind())
      .onComplete(_ => system.terminate())
  }

}
