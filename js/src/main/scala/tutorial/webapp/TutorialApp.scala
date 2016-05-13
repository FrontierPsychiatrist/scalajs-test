package tutorial.webapp

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{ReactComponentB, ReactDOM}
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.raw.XMLHttpRequest
import tutorial.Person

import scala.scalajs.js.JSApp

object TutorialApp extends JSApp {
  def main(): Unit = {
    println("Hello world!")

    val HelloMessage = ReactComponentB[Person]("HelloMessage")
      .render($ => <.div("Hello", $.props.name, "Foo"))
      .build

    val xhr = new XMLHttpRequest()
    xhr.open("GET", "http://localhost:5000/test")
    xhr.onload = (e: Event) => {
      if(xhr.status == 200) {
        val person = upickle.default.read[Person](xhr.responseText)
        ReactDOM.render(HelloMessage(person), document.body)
      }
    }
    xhr.send()
  }
}