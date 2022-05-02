package aview
import model.Move
import model.Field
import util.Observer
import controller.Controller
import scala.io.StdIn.readLine
import java.lang.System.exit

class TUI(controller: Controller) extends Observer {
    controller.add(this)
    var player = 0
    var moveMethod: Move => Field = null
    var moveParams: Move = null

    def run = {
        getInputAndLoop()
    }
    override def update = println(controller.field.toString)

    def getInputAndLoop(): Unit = {
        printField()
        getInput()
        controller.doAndPublish(moveMethod, moveParams)
        getInputAndLoop()
    }
    def printField() = {
        print("\u001b[2J")
        println("\u001b[33m" + controller.field.players(player).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | l-destroy(slt) | r-decHP(amnt)\ni-incHP(ammount) | m-redMana(amnt) | n-incMana(amnt) | s-Endturn\u001b[0m")

    }
    def switchPlayer() = if(player == 0) then player = 1 else player = 0

    def getInput() = {
        val input = readLine
        val chars = input.toCharArray

        chars(0) match
            case 'q' => exit(0)
            case 'p' => moveMethod = controller.placeCard; moveParams = Move(playerID = player, chars(1).asDigit - 1, chars(2).asDigit - 1)
            case 'd' => moveMethod = controller.drawCard; moveParams = Move(playerID = player)
            case 'l' => moveMethod = controller.destroyCard; moveParams = Move(playerID = player, fieldSlot = chars(2).asDigit)
            case 'r' => moveMethod = controller.reduceHp; moveParams = Move(playerID = player, amount  = (chars(1).toString + chars(2).toString).toInt)
            case 'i' => moveMethod = controller.increaseHp; moveParams = Move(playerID = player, amount  = (chars(1).toString + chars(2).toString).toInt)
            case 'm' => moveMethod = controller.reduceMana; moveParams = Move(playerID = player, amount  = (chars(1).toString + chars(2).toString).toInt)
            case 'n' => moveMethod = controller.increaseMana; moveParams = Move(playerID = player, amount = (chars(1).toString + chars(2).toString).toInt)
            case 's' => switchPlayer()
    }
}

// <taste/befehl> <playerID> <argumente1> <argument2>
// p = placecard(id, hand, feld)
// d = drawcard(id)
// l = destroycard(id, fieldslot)
// r = reduceHP(id, amount)
// i = increaseHP(id, amount)
// m = reduceMana(id, amount)
// n = increaseMana(id, amount)
