package aview

import model.Move
import util.Observer
import util.Event
import controller.GameState
import controller.Strategy
import controller.component.controllerImpl.Controller
import model.field_component.FieldInterface

import scala.io.StdIn
import scala.util.{Failure, Success, Try}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class TUI(controller: Controller) extends Observer {
    controller.add(this)

    override def update(e: Event) = {
        e match {
            case Event.EXIT => println("\u001b[2J" + "Schönes Spiel!")
            case Event.PLAY => {
                controller.gameState match {
                    case GameState.CHOOSEMODE => println("Bitte Spielmodus auswählen: " +
                      "\n[1] Normal: Start mit: 30 Healthpoints & 1 Mana" +
                      "\n[2] Hardcore: Start mit: 10 Healthpoints & 5 Mana " +
                      "\n[3] Admin: Start mit: 100 Healthpints & 100 Mana")
                    case GameState.ENTERPLAYERNAMES =>  println("Bitte Spielernamen 1 & 2 eingeben: ")
                    case GameState.MAINGAME => printField()
                    case GameState.WIN => checkWinner()
                }
            }
        }
    }


    def onInput(input: String) = {
        checkInput(input) match {
            case Failure(_) =>
            case Success(_) => controller.gameState match {
                case GameState.CHOOSEMODE => setGameStrategy(input)
                case GameState.ENTERPLAYERNAMES => setPlayerNames(input)
                case GameState.MAINGAME => checkInputAndLoop(input)
                case GameState.WIN => checkWinner()
            }
        }
    }

    def setGameStrategy(input: String): Unit = {
        var strategy: FieldInterface = null
        input.toCharArray.apply(0).asDigit match {
            case 1 => strategy = Strategy.normalStrategy()
            case 2 => strategy = Strategy.hardcoreStrategy()
            case 3 => strategy = Strategy.adminStrategy()
        }
        controller.setStrategy(strategy)
    }
    def setPlayerNames(input: String): Unit = {
        val splitInput = input.split(" ")
        controller.setPlayerNames(Move(p1 = splitInput(0), p2 = splitInput(1)))
    }
    def printField(): Unit = {
        print("\u001b[2J")
        println("\u001b[33m" + controller.field.players(0).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | a-attack(yours, theirs) | e-direct attack | s-Endturn | z-undo | y-redo | q-Quit\u001b[0m")

    }
    def checkInputAndLoop(input: String): Unit = {
        checkInput(input) match {
            case Failure(_) =>
            case Success(_) => evlInput(input)
        }
    }

    def checkInput(input: String): Try[String] = {
        controller.gameState match {
            case GameState.CHOOSEMODE => if (input.matches("([123])")) then Success(input) else Failure(Exception(""))
            case GameState.ENTERPLAYERNAMES => if (input.matches("(.{3,10}\\s.{3,10})")) then Success(input) else Failure(Exception(""))
            case GameState.MAINGAME => if (input.matches("([pa]\\d\\d)|([qdszy])|([e]\\d)")) then Success(input) else Failure(Exception(""))
        }
    }

    def evlInput(input: String) = {
        val chars = input.toCharArray
        chars(0) match
            case 'q' => controller.exitGame()
            case 'p' => controller.placeCard(Move(chars(1).asDigit - 1, chars(2).asDigit - 1))
            case 'd' => {if (controller.field.players(0).gamebar.hand.length < 5) then controller.drawCard()}
            case 'a' => controller.attack(Move(fieldSlotActive = chars(1).asDigit - 1, fieldSlotInactive = chars(2).asDigit - 1))
            case 'e' => controller.directAttack(Move(fieldSlotActive = chars(1).asDigit - 1))
            case 's' => controller.switchPlayer()
            case 'z' => controller.undo
            case 'y' => controller.redo
    }

    def checkWinner(): Unit = {
        val p1Hp = controller.field.players(0).gamebar.hp.isEmpty()
        val p2Hp = controller.field.players(1).gamebar.hp.isEmpty()

        if p1Hp then println("\n" + controller.field.players(1).name + " hat gewonnen!!")
        else println("\n" + controller.field.players(0).name + " hat gewonnen!!")
    }
}

