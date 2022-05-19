package aview
import model.Move
import model.Field
import util.Observer
import controller.Controller
import controller.GameState
import controller.Strategy
import scala.io.StdIn.readLine


class TUI(controller: Controller) extends Observer {
    controller.add(this)

    def run = {
        update
    }
    override def update = {
        controller.gameState match {
            case GameState.PREGAME => preGame()
            case GameState.MAINGAME => getInputAndLoop()
            case GameState.ERROR => println(GameState.message(controller.gameState))
            case GameState.EXIT => println("\u001b[2J" + GameState.message(controller.gameState))
        }
    }
    def preGame(): Unit = {
        controller.gameState = GameState.MAINGAME
        setGameStrategy()
        setPlayerNames()
    }
    def setGameStrategy(): Unit = {
        println("Bitte Spielmodus auswählen: " +
          "\n[1] Normal: Start mit: 30 Healthpoints & 1 Mana" +
          "\n[2] Hardcore: Start mit: 10 Healthpoints & 1 Mana " +
          "\n[3] Admin: Start mit: 100 Healthpints & 100 Mana")
        val input = readLine
        input.toCharArray.apply(0).asDigit match {
            case 1 => controller.field = Strategy.normalStrategy()
            case 2 => controller.field = Strategy.hardcoreStrategy()
            case 3 => controller.field = Strategy.adminStrategy()
            case _ => setGameStrategy()
        }
    }
    def setPlayerNames(): Unit = {
        println("Bitte Spielername 1 eingeben: ")
        val playername1 = readLine
        println("Bitte Spielername 2 eingeben: ")
        val playername2 = readLine
        controller.doAndPublish(controller.setPlayerNames, Move(p1 = playername1, p2 = playername2))
    }
    def printField(): Unit = {
        print("\u001b[2J")
        println("\u001b[33m" + controller.field.players(0).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | a-attack(your-field, their-field) | s-Endturn | q-Quit\u001b[0m")
        
    }
    def getInputAndLoop(): Unit = { 
        printField()
        val input: String = readLine
        checkInput(input) match {
            case false => {}
            case true => {
                val (method, params) = getInput(input)
                controller.doAndPublish(method, params)
            }
        }    
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

