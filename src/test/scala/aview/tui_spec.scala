package aview

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import controller.Controller
import model.Field
import model.Move

class TuiSpec extends AnyWordSpec with Matchers {
   "The TUI" should {
        val controller = Controller(new Field(5, "jan", "sam"))
        val tui = TUI(controller)
        "recognize the input 'd' as a move" in {
        tui.getInput("d") shouldBe a [(Move => Field, Move)]
    }
  }
}