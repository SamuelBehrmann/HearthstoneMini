package aview

import javax.sound.sampled.Control
import java.util.Observer
import controller.Controller
import scala.io.StdIn.readLine
import java.lang.System.exit

class TUI(controller: Controller) extends Observer {
    controller.addObserver(this)
    var player = 0

    def run = {
        getInputAndLoop()
    }
    override def update(we: java.util.Observable, obj:Object) = println(controller.field.toString)

    def getInputAndLoop(): Unit = {
        val input = readLine
        val chars = input.toCharArray

        chars(0) match
            case 'q' => exit(0)
            case 'p' => controller.placeCard(player, chars(1).asDigit - 1, chars(2).asDigit - 1)
            case 'd' => controller.drawCard(player)
            case 'l' => controller.destroyCard(player, chars(1).asDigit - 1)
            case 'r' => controller.reduceHp(player, (chars(1).toString + chars(2).toString).toInt)
            case 'i' => controller.increaseHp(player, (chars(1).toString + chars(2).toString).toInt)
            case 'm' => controller.reduceMana(player, (chars(1).toString + chars(2).toString).toInt)
            case 'n' => controller.increaseMana(player, (chars(1).toString + chars(2).toString).toInt)
            case 's' => switchPlayer()
        printField()
        getInputAndLoop()
    }
    def printField() = {
        println("\u001b[33m" + controller.field.players(player).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | l-destroy(slt) | r-decHP(amnt)\ni-incHP(ammount) | m-redMana(amnt) | n-incMana(amnt) | s-Endturn\u001b[0m")
    }
    def switchPlayer() = if(player == 0) then player = 1 else player = 0
}



// <taste/befehl> <playerID> <argumente1> <argument2>
// p = placecard(id, hand, feld)
// d = drawcard(id)
// l = destroycard(id, fieldslot)
// r = reduceHP(id, amount)
// i = increaseHP(id, amount)
// m = reduceMana(id, amount)
// n = increaseMana(id, amount)
