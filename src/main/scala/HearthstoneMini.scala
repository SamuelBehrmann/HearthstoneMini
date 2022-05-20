//package scala

import model._

import controller.Controller
import aview.TUI
import scala.io.StdIn.readLine

@main
def run(): Unit = {
    val field = new Field(5)
    val controller = Controller(field)
    val tui = TUI(controller)

    controller.notifyObservers
}



