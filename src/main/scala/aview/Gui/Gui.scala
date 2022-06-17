package aview.Gui

import aview.Gui.enterPlayernamesScreen.EnterPlayernamesScreenImpl
import aview.Gui.mainGameScreen.MainGameScreen
import aview.Gui.modeSelectionScreen.ModeSelectionScreenImpl
import controller.GameState
import controller.component.controllerImpl.Controller
import scalafx.application.{JFXApp3, Platform}
import scalafx.scene.Scene
import scalafx.scene.paint.Color.*


class GUI(guiApp: GUIApp, controller: Controller) extends JFXApp3
{
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "HearthstoneMini"
      width = 700
      height = 620

      scene = new Scene {
        fill = White
        Platform.runLater {
          () -> {
            controller.gameState match {
              case GameState.CHOOSEMODE => content = new ModeSelectionScreenImpl(controller = controller)
              case GameState.ENTERPLAYERNAMES => content = new EnterPlayernamesScreenImpl(controller = controller)
              case GameState.MAINGAME => content = new MainGameScreen(controller = controller)
              case GameState.WIN => stopApp()
            }
          }
        }
      }
    }
  }
}



