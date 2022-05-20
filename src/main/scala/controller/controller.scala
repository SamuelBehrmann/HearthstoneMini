package controller

import util.Observable
import model.Field
import model.Player
import model.Move
import controller.GameState._
import java.lang.System.exit

case class Controller(var field: Field) extends Observable {
     var gameState: GameState = GameState.PREGAME

     def placeCard(move: Move) = field.placeCard(move.handSlot, move.fieldSlotActive)
     def drawCard(move: Move) = field.drawCard()
     def setPlayerNames(move: Move) = field.setPlayerNames(move.p1,move.p2)
     def attack(move: Move) = {
          val difference = Math.abs(field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue - field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue)
          if(field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue < field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue) then
               field.destroyCard(0, move.fieldSlotActive).reduceHp(0, difference)
          else if(field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue > field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue) then
               field.destroyCard(1, move.fieldSlotInactive).reduceHp(1, difference)
          else if(field.players(0).fieldbar.cardArea.slot(move.fieldSlotActive).get.attValue == field.players(1).fieldbar.cardArea.slot(move.fieldSlotInactive).get.defenseValue) then
               field.destroyCard(0, move.fieldSlotActive).destroyCard(1, move.fieldSlotInactive)
          else {
               gameState = GameState.ERROR
               field
          }

     }
     def switchPlayer(move: Move) = {
          field.switchPlayer()
     }
     def exitGame(move: Move) = {
          gameState = GameState.EXIT
          field
     }
     def doAndPublish(doThis: Move => Field, move: Move ) = {
          field = doThis(move)
          notifyObservers
     }
     override def toString() = field.toString
}