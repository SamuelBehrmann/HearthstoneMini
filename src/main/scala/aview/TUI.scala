package aview
import model.Move
import model.Field
import util.Observer
import controller.Controller
import scala.io.StdIn.readLine


class TUI(controller: Controller) extends Observer {
    controller.add(this)

    def run = {
        getPlayerNames()
        getInputAndLoop()
    }
    override def update = println(controller.field.toString)

    def getInputAndLoop(): Unit = {
        if (!controller.doExit) then {
            printField()
            val input: String = readLine
            checkInput(input) match {
                case false => {}
                case true => {
                    val (method, params) = getInput(input)
                    controller.doAndPublish(method, params)
                }
            }
            getInputAndLoop()
        }
    }
    def getPlayerNames() = {
        println("Bitte Spielername 1 eingeben: ")
        val playername1 = readLine
        println("Bitte Spielername 2 eingeben: ")
        val playername2 = readLine
        controller.doAndPublish(controller.setPlayerNames, Move(p1 = playername1, p2 = playername2))
    }
    def printField() = {
        print("\u001b[2J")
        println("\u001b[33m" + controller.field.players(controller.player).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | l-destroy(slt) | r-decHP(amnt)\ni-incHP(ammount) | m-redMana(amnt) | n-incMana(amnt) | s-Endturn\u001b[0m")

    }
    def checkInput(input: String) = input.matches("([qpdlrimns]\\d\\d)|([a-z]\\d)|([a-z])")

    def getInput(input: String): (Move => Field, Move) = {
        val chars = input.toCharArray
        chars(0) match
            case 'q' => (controller.exitGame, Move())
            case 'p' => (controller.placeCard, Move(chars(1).asDigit - 1, chars(2).asDigit - 1))
            case 'd' => (controller.drawCard, Move())
            case 'l' => (controller.destroyCard, Move( fieldSlot = chars(1).asDigit - 1))
            case 'r' => (controller.reduceHp, Move( amount  = (chars(1).toString + chars(2).toString).toInt))
            case 'i' => (controller.increaseHp, Move( amount  = (chars(1).toString + chars(2).toString).toInt))
            case 'm' => (controller.reduceMana, Move( amount  = (chars(1).toString + chars(2).toString).toInt))
            case 'n' => (controller.increaseMana, Move( amount = (chars(1).toString + chars(2).toString).toInt))
            case 's' => (controller.switchPlayer, Move()) // return old Field and Empty move
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
