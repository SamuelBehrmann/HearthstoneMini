package controller

import util.Observable
import model.Field
import model.Player
import model.Move
import java.lang.System.exit
case class Controller(var field: Field) extends Observable {
     var player = 0
     var doExit = false

     def placeCard(move: Move) = field.placeCard(move.handSlot, move.fieldSlot)
     def destroyCard(move: Move) = field.destroyCard(move.fieldSlot)
     def drawCard(move: Move) = field.drawCard()
     def reduceHp(move: Move) = field.reduceHp( move.amount)
     def increaseHp(move: Move) = field.increaseHp(move.amount)
     def reduceMana(move: Move) = field.reduceMana(move.amount)
     def increaseMana(move: Move) = field.increaseMana(move.amount)
     def setPlayerNames(move: Move) = field.setPlayerNames(move.p1,move.p2)
     def switchPlayer(move: Move) = {
          if(player == 0) then player = 1 else player = 0
          field
     }
     def exitGame(move: Move) = {
          doExit = true
          field
     }
     def doAndPublish(doThis: Move => Field, move: Move ) = {
          field = doThis(move)
          notifyObservers
     }
     override def toString() = field.toString
}