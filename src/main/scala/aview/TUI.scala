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
        //update
        getInputAndLoop()
    }
    override def update = {
        print("\u001b[2J")
        println("turns: " + controller.field.turns + "\u001b[33m" + controller.field.players(0).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | a-attack(your-field, their-field) | s-Endturn | q-Quit\u001b[0m")
    }

    def getInputAndLoop(): Unit = {
        if (!controller.doExit) then {
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
        println("turns: " + controller.field.turns + "\u001b[33m" + controller.field.players(0).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | a-attack(your-field, their-field) | s-Endturn | q-Quit\u001b[0m")

    }
    def checkInput(input: String) = input.matches("([qpdas]\\d\\d)|([a-z]\\d)|([a-z])")

    def getInput(input: String): (Move => Field, Move) = {
        val chars = input.toCharArray
        chars(0) match
            case 'q' => (controller.exitGame, Move())
            case 'p' => (controller.placeCard, Move(chars(1).asDigit - 1, chars(2).asDigit - 1))
            case 'd' => (controller.drawCard, Move())
            case 'a' => (controller.attack, Move(fieldSlotActive = chars(1).asDigit - 1, fieldSlotInactive = chars(2).asDigit - 1))
            case 's' => (controller.switchPlayer, Move())
    }

}

