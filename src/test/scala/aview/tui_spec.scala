package aview

import controller.component.controllerImpl.Controller
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import model.field_component.fieldImpl.FieldInterface
import util.Observer
import scala.util.Try
import scala.util.Success


class StrategySpec extends AnyWordSpec with Matchers {
  "The Tui" when {
    val tui = new TUI(Controller(FieldInterface()))
    "checkInput is used" should {
      "return a Try" in {
        tui.checkInput("1").isSuccess should be (true)
      }
    }
  }
}

