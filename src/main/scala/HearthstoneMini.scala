//package scala

import model._

import controller.Controller
import aview.TUI
import aview.GUI
import scala.io.StdIn.readLine
import util.Event

@main
def run(): Unit = {
    val field = new Field(5)
    val controller = Controller(field)
    controller.add(GUI)
    GUI.start()
    val tui = TUI(controller)
    tui.update(Event.PLAY)
    //controller.notifyObservers(Event.PLAY)
}



