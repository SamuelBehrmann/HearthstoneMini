package hearthstoneMini

import aview.Gui.GUIApp
import model.*
import controller.GameState
import aview.Tui
import controller.component.controllerImpl.Controller
import model.fieldComponent.fieldImpl.Field
import scala.io.StdIn
import scala.io.StdIn.readLine
import util.Event

object HearthstoneMini {
  val hearthstoneMiniRunner = new HearthstoneMiniRunner()

  def main(args: Array[String]): Unit = {
    hearthstoneMiniRunner.play()
  }
}

class HearthstoneMiniRunner(initGUI: Boolean = false) {
  val controller: Controller = Controller(new Field(5))
  val tui: Tui = Tui(controller)
  val optionalGUI: Option[GUIApp] = if (initGUI) Some(new GUIApp(controller)) else None

  def play(): Unit = {
    tui.update(Event.PLAY, None)
    while controller.gameState != GameState.EXIT && controller.gameState != GameState.WIN do {
      tui.onInput(StdIn.readLine())
    }
  }
}





