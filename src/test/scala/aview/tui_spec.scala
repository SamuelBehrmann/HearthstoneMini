package aview

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import controller.Controller
import util.Observer
import model.Field
import scala.util.Try
import scala.util.Success


class StrategySpec extends AnyWordSpec with Matchers {
  "The Tui" when {
    val tui = new TUI(Controller(Field()))
    "checkInput is used" should {
      "return a Try" in {
        tui.checkInput("a11").isSuccess should be (true)
      }
    }
  }
}

