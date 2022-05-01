//package scala

import model._

import controller.Controller
import aview.TUI
import scala.io.StdIn.readLine

import org.json4s
import org.json4s.jackson.JsonMethods._
//import scala.util.Random
@main
def run(): Unit = {

    printJson()
//    println("Bitte Spielername 1 eingeben: ")
//    val playername1 = readLine
//    println("Bitte Spielername 2 eingeben: ")
//    val playername2 = readLine
//    val field = new Field(size = 5, player1 = playername1, player2 = playername2)
//    val controller = Controller(field)
//    val tui = TUI(controller)
//
//    tui.run
    //TODO: deck mischen implementieren
    //TODO: spielfluß einbauen
    //TODO: Decks erstellen und importieren
    //TODO:
}

def printJson() = {val jsonString = os.read(os.pwd/"src"/"main"/"scala"/"model"/"jsonStuff"/"test.json")
    val data = ujson.read(jsonString)
   // print(data.value)

    val cardString = os.read(os.pwd/"src"/"main"/"scala"/"model"/"jsonStuff"/"cards.json")
    //val data2 = ujson.read(cardString)['data']
    //data2.arr(0)
    //print(data2.foreach(_(0)))
}

