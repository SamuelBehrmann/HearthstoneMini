package controller

import util.Observable
import model.Field
import model.Player
import model.Move
import java.lang.System.exit
case class Controller(var field: Field) extends Observable {
     var player = 0

     def placeCard(move: Move) = {
          field.placeCard(player, move.handSlot, move.fieldSlot)
     }
     def destroyCard(move: Move) = {
          field.destroyCard(player, move.fieldSlot)
     }
     def drawCard(move: Move) = {
          field.drawCard(player)
     }
     def reduceHp(move: Move) = {
          field.reduceHp(player, move.amount)
     }
     def increaseHp(move: Move) = {
          field.increaseHp(player, move.amount)
     }
     def reduceMana(move: Move) = {
          field.reduceMana(player, move.amount)
     }
     def increaseMana(move: Move) = {
          field.increaseMana(player, move.amount)
     }
     def switchPlayer(move: Move) = {
          if(player == 0) then player = 1 else player = 0 
          field
     }
     def exitGame(move: Move) = {
          exit(0)
          field
     }
     def setPlayerNames(move: Move) = {
          field.setPlayerNames(move.p1,move.p2)
     }

     def doAndPublish(doThis: Move => Field, move: Move ) = {
          field = doThis(move)
          notifyObservers
     }

     override def toString() = field.toString
}