
import aview.Gui.GUIApp
import model.*
import controller.GameState
import aview.Tui
import controller.component.controllerImpl.Controller
import model.fieldComponent.fieldImpl.Field

import scala.io.StdIn.readLine
import util.Event
import scalafx.application.Platform

import scala.concurrent.*
import java.time.Duration
import org.scalactic.Bool

import scala.io.StdIn

@main
def run: Unit = {
    val controller = Controller(new Field(5))
    val tui = Tui(controller)
    val GUI = new GUIApp(controller)
    tui.update(Event.PLAY, None)
    while (controller.gameState != GameState.EXIT) do {
        tui.onInput(StdIn.readLine())
    }


}



