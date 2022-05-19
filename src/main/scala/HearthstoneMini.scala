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

    tui.run
    //TODO: deck mischen implementieren
    //TODO: spielfluß einbauen
    //TODO: Decks erstellen und importieren
    //TODO: contraints implementieren für methoden und HP
    //TODO: friedhof - leere karten
    //TODO: Hp balken geht über bildschirm rand hinaus
    //TODO: belegte felder können überschrieben werden
    //TODO: mana kan negativ werden
    //TODO: spiel ende wenn hp = 0 sind
    //TODO: angriffe etc implementieren
    //TODO: karten einlesen
    //TODO:

}



