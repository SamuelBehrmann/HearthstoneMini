package hearthstoneMini
package aview

import model.Move
import util.Observer
import util.Event
import controller.GameState
import controller.Strategy
import controller.component.controllerImpl.Controller
import model.fieldComponent.FieldInterface

import scala.io.StdIn
import scala.util.{Failure, Success, Try}
import scala.concurrent.Future
import scala.concurrent.Await
import scala.concurrent.duration.Duration

class Tui(controller: Controller) extends Observer {
    controller.add(this)

    override def update(e: Event, msg: Option[String]) = {
        e match {
            case Event.ERROR => msg.fold({})((msg) => println(msg))
            case Event.EXIT => println("\u001b[2J" + "Schönes Spiel!")
            case Event.PLAY => {
                controller.gameState match {
                    case GameState.CHOOSEMODE => println("Bitte Spielmodus auswählen: " +
                      "\n[1] Normal: Start mit: 30 Healthpoints & 1 Mana" +
                      "\n[2] Hardcore: Start mit: 10 Healthpoints & 5 Mana " +
                      "\n[3] Admin: Start mit: 100 Healthpints & 100 Mana")
                    case GameState.ENTERPLAYERNAMES =>  println("Bitte Spielernamen 1 & 2 eingeben: ")
                    case GameState.MAINGAME => printField()
                    case GameState.WIN => println("\n" + controller.getWinner().getOrElse(" ") + " hat gewonnen!!")
                }
            }
        }
    }


    def onInput(input: String) = {
        if checkInput(input) then {
            controller.gameState match {
                case GameState.CHOOSEMODE => setGameStrategy(input)
                case GameState.ENTERPLAYERNAMES => setPlayerNames(input)
                case GameState.MAINGAME => EvalInput(input)
            }
        }
    }

    def setGameStrategy(input: String): Unit = {
        controller.setStrategy(input.toCharArray.head.asDigit match {
            case 1 => Strategy.normalStrategy()
            case 2 => Strategy.hardcoreStrategy()
            case 3 => Strategy.adminStrategy()
        })
    }
    def setPlayerNames(input: String): Unit = {
        val splitInput = input.split(" ")
        controller.setPlayerNames(Move(p1 = splitInput(0), p2 = splitInput(1)))
    }
    def printField(): Unit = {
        print("\u001b[2J")
        println("\u001b[33m" + controller.field.players(0).name + " ist dran!\u001b[0m")
        println(controller.field.toString)
        println("\u001b[33mp-place(hand,solt) | d-draw() | a-attack(yours, theirs) | e-direct attack | " +
          "s-Endturn | z-undo | y-redo | q-Quit\u001b[0m")

    }
    def checkInput(input: String): Boolean = {
        controller.gameState match {
            case GameState.CHOOSEMODE => input.matches("([123])")
            case GameState.ENTERPLAYERNAMES => input.matches("(.{3,10}\\s.{3,10})")
            case GameState.MAINGAME => input.matches("([pa]\\d\\d)|([qdszy])|([e]\\d)")
        }
    }
    def EvalInput(input: String): Unit = {
        val chars = input.toCharArray
        chars(0) match
            case 'q' => controller.exitGame()
            case 'p' => controller.placeCard(Move(chars(1).asDigit - 1, chars(2).asDigit - 1))
            case 'd' => controller.drawCard()
            case 'a' => controller.attack(Move(fieldSlotActive = chars(1).asDigit - 1,
                fieldSlotInactive = chars(2).asDigit - 1))
            case 'e' => controller.directAttack(Move(fieldSlotActive = chars(1).asDigit - 1))
            case 's' => controller.switchPlayer()
            case 'z' => controller.undo
            case 'y' => controller.redo
    }
    override def toString(): String =  "\u001b[2J" + 
        "\n\u001b[33m" + controller.field.players(0).name + " ist dran!\u001b[0m" +
        "\n" + controller.field.toString + 
        "\n\u001b[33mp-place(hand,solt) | d-draw() | a-attack(yours, theirs) | e-direct attack | " +
          "s-Endturn | z-undo | y-redo | q-Quit\u001b[0m"
}

