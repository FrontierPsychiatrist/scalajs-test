package tutorial.webapp

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{ReactComponentB, ReactDOM}
import org.scalajs.dom.document

import scala.scalajs.js.JSApp

case class Person(name: String)

object TutorialApp extends JSApp {
  def main(): Unit = {
    println("Hello world!")

    val HelloMessage = ReactComponentB[Person]("HelloMessage")
      .render($ => <.div("Hello", $.props.name, "Foo"))
      .build

    ReactDOM.render(HelloMessage(Person("Moritz")), document.body)
  }
}