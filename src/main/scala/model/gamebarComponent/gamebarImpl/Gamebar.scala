package model.gamebarComponent.gamebarImpl

import model.cardComponent.cardImpl.Card
import model.fieldComponent.fieldImpl.{Field, FieldObject}
import model.fieldComponent.FieldInterface
import model.matrixComponent.matrixImpl.Matrix

import scala.collection.View.Empty
import scala.compiletime.ops.string
import model.healthpointsComponent.healthpointsImpl.Healthpoints
import model.manaComponent.manaImpl.Mana
import model.gamebarComponent.GamebarInterface
import util.CardProvider

case class Gamebar(hp: Healthpoints = new Healthpoints(30, 30),
                   mana: Mana = new Mana(),
                   hand: List[Card] = new CardProvider("src/main/scala/model/json/cards.json").getCards(5),
                   deck: List[Card] = new CardProvider("src/main/scala/model/json/cards.json").getCards(30),
                   friedhof: Array[Card] = Array[Card]()) extends GamebarInterface{

    def removeCardFromHand(slot: Int): Gamebar = copy(hand = hand.filter(_ != hand(slot)))
    def addCardToHand(card: Option[Card]): Gamebar = copy(hand = hand.appended(card.get))
    def addCardToFriedhof(card: Option[Card]): Gamebar = card match {
        case Some(_) => copy(friedhof = friedhof.appended(card.get))
        case None => this
    }
    def reduceHp(amount: Int): Gamebar = copy(hp = hp.decrease(amount))
    def increaseHp(amount: Int): Gamebar = copy(hp = hp.increase(amount))
    def reduceMana(amount: Int): Gamebar = copy(mana = mana.decrease(amount))
    def increaseMana(amount: Int): Gamebar = copy(mana = mana.increase(amount))
    def resetAndIncreaseMana(): Gamebar = copy(mana = mana.resetAndIncrease())
    def drawCard(): Gamebar = copy(hand = hand.appended(deck(0)), deck = deck.filter(_ != deck(0)))
    def setManaValue(amount: Int): Gamebar = copy(mana = mana.setVal(amount))
    def setHpValue(amount: Int): Gamebar = copy(hp = hp.setVal(amount))
    def handAsMatrix(): Matrix[String] = {
        var tmpMatrix =  new Matrix[String](FieldObject.standartCardHeight, FieldObject.standartFieldWidth, " ")
        hand.zipWithIndex.foreach((elem,index) => tmpMatrix = tmpMatrix.updateMatrixWithMatrix(0, FieldObject.standartSlotWidth * index + 1, hand(index).toMatrix()))
        tmpMatrix
    }

    def toMatrix: Matrix[String] = new Matrix[String](FieldObject.standartGameBarHeight, FieldObject.standartFieldWidth, " ")
    .updateMatrix(0,0,List[String]("\u001b[32mHP: " + hp.toString + " \u001b[0;34mMana: " + mana.toString + "\u001b[0;37m"))
    .updateMatrix(0,FieldObject.standartFieldWidth - 2, List[String]("\u001b[0;31mDeck: " + deck.length + "  Friedhof: " + friedhof.length + "\u001b[0;37m"))
    .updateMatrixWithMatrix(1,0, handAsMatrix())
    .updateMatrix(6,0,List[String]("-" * FieldObject.standartFieldWidth))
}
