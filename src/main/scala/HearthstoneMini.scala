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
    //TODO: deck mischen implementieren
    //TODO: Decks erstellen und importieren
    //TODO: contraints implementieren für methoden und HP /friedhof leere karten / 
    //TODO: belegte felder können überschrieben werden
    //TODO: spiel ende wenn hp = 0 sind
    //TODO: karten einlesen
}



