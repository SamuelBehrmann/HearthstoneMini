package controller

import util.Observable
import model.Field
import model.Player
import model.Move

case class Controller(var field: Field) extends Observable {
    def placeCard(move: Move) = {
            field.placeCard(move.playerID, move.handSlot, move.fieldSlot)
    }
    def destroyCard(move: Move) = {
        field.destroyCard(move.playerID, move.fieldSlot)
    }
    def drawCard(move: Move) = {
         field.drawCard(move.playerID)
    }
    def reduceHp(move: Move) = {
         field.reduceHp(move.playerID, move.amount)
    }
    def increaseHp(move: Move) = {
         field.increaseHp(move.playerID, move.amount)
    }
    def reduceMana(move: Move) = {
         field.reduceMana(move.playerID, move.amount)
    }
    def increaseMana(move: Move) = {
         field.increaseMana(move.playerID, move.amount)
    }
    def doAndPublish(doThis: Move => Field, move: Move ) = {
        field = doThis(move)
        notifyObservers
    }

    override def toString() = field.toString
}