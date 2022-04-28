package scala

import model._
import controller.Controller
import aview.TUI
@main
def run(): Unit = {
    val field = new Field(size = 5, player1 = "Heinrich", player2 = "Peter")
    val controller = Controller(field)
    val tui = TUI(controller)

    tui.run
    //TODO: methoden umstellen sodass eine spieler-ID übergebenwerden kann
    //TODO: deck mischen implementieren
    //TODO: Tests schreiben
    //TODO: Scalable
}
