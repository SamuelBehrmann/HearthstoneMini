package controller

import java.util.Observable
import model.Field
import model.Player

case class Controller(var field: Field) extends Observable {
    def placeCard(playerID: Int, handSlot: Int, fieldSlot: Int) = {
        field = field.placeCard(playerID, handSlot, fieldSlot)
        notifyObservers
    }
    def destroyCard(playerID: Int, fieldSlot: Int) = {
        field = field.destroyCard(playerID, fieldSlot)
        notifyObservers
    }
    def drawCard(playerID: Int) = {
        field = field.drawCard(playerID)
        notifyObservers
    }
    def reduceHp(playerID: Int, amount: Int) = {
        field = field.reduceHp(playerID, amount)
        notifyObservers
    }
    def increaseHp(playerID: Int, amount: Int) = {
        field = field.increaseHp(playerID, amount)
        notifyObservers
    }
    def reduceMana(playerID: Int, amount: Int) = {
        field = field.reduceMana(playerID, amount)
        notifyObservers
    }
    def increaseMana(playerID: Int, amount: Int) = {
        field = field.increaseMana(playerID, amount)
    }

    override def toString() = field.toString
}