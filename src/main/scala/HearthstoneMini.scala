//package scala

import model._

import controller.Controller
import aview.TUI
import scala.io.StdIn.readLine
//import scala.util.Random
@main
def run(): Unit = {
    println("Bitte Spielername 1 eingeben: ")
    val playername1 = readLine
    println("Bitte Spielername 2 eingeben: ")
    val playername2 = readLine
    val field = new Field(size = 5, player1 = playername1, player2 = playername2)
    val controller = Controller(field)
    val tui = TUI(controller)

    tui.run
    //TODO: deck mischen implementieren
    //TODO: spielfluß einbauen
    //TODO: Decks erstellen und importieren
    //TODO:
    //yolo swag what ever
}

