package tutorial.webapp

import japgolly.scalajs.react.vdom.prefix_<^._
import japgolly.scalajs.react.{BackendScope, Callback, ReactComponentB, ReactDOM}
import org.scalajs.dom.document
import org.scalajs.dom.ext.Ajax
import tutorial.Person

import scala.scalajs.js.JSApp

// argh, why?
import scala.concurrent.ExecutionContext.Implicits.global

case class State(person: Person)

class Backend($: BackendScope[Unit, State]) {
  def handleClick: Callback = {
    Callback.future[Unit](
      Ajax.get("http://localhost:5000/test")
      .map(xhr => {
        val person = upickle.default.read[Person](xhr.responseText)
        $.modState(_.copy(person = person))
      }
    ))
  }

  def render(state: State) = <.div(
    <.span("Hello ", state.person.name),
    <.button("Load", ^.onClick --> handleClick)
  )
}

object TutorialApp extends JSApp {
  def main(): Unit = {
    val Application = ReactComponentB[Unit]("Application")
      .initialState(State(Person("Empty")))
      .renderBackend[Backend]
      .build

    ReactDOM.render(Application(), document.body)
  }
}
