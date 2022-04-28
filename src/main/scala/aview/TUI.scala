package aview

import javax.sound.sampled.Control
import java.util.Observer
import controller.Controller
import scala.io.StdIn.readLine

class TUI(controller: Controller) extends Observer {
    controller.addObserver(this)
    def run = {
        println(controller.field.toString)
        getInputAndLoop()
    }
    override def update(we: java.util.Observable, obj:Object) = println(controller.field.toString)

    def getInputAndLoop(): Unit = {
        val input = readLine
        val chars = input.toCharArray
        val player = chars(1).toInt
        print(player)
        chars(0) match 
            case 'p' => controller.placeCard(player, 1, 1)
        
        println(controller.toString)
        getInputAndLoop()
    }
}


// <taste/befehl> <playerID> <argumente1> <argument2>
// q = placecard(id, hand, feld)
// q115