package model

import scala.compiletime.ops.string
import scala.collection.View.Empty
import util.CardProvider

case class GameBar(hp: Healthpoints = new Healthpoints(30, 30),
    mana: Mana = new Mana(),
    hand: List[Card] = new CardProvider("src/main/scala/model/jsonStuff/cards.json").getCards(5),
    deck: List[Card] = new CardProvider("src/main/scala/model/jsonStuff/cards.json").getCards(30),
    friedhof: Array[Card] = Array[Card]()) {

    def removeCardFromHand(slot: Int): GameBar = copy(hand = hand.filter(_ != hand(slot)))
    def addCardToHand(card: Option[Card]): GameBar = copy(hand = hand.appended(card.get))
    def addCardToFriedhof(card: Option[Card]): GameBar = card match {
        case Some(_) => copy(friedhof = friedhof.appended(card.get))
        case None => this
    }
    def reduceHp(amount: Int): GameBar = copy(hp = hp.decrease(amount))
    def increaseHp(amount: Int): GameBar = copy(hp = hp.increase(amount))
    def reduceMana(amount: Int): GameBar = copy(mana = mana.decrease(amount))
    def increaseMana(amount: Int): GameBar = copy(mana = mana.increase(amount))
    def resetAndIncreaseMana(): GameBar = copy(mana = mana.resetAndIncrease())
    def drawCard(): GameBar = copy(hand = hand.appended(deck(0)), deck = deck.filter(_ != deck(0)))
    def setManaValue(amount: Int) = copy(mana = mana.setVal(amount))
    def setHpValue(amount: Int) = copy(hp = hp.setVal(amount))
    def handAsMatrix(): Matrix[String] = {
        var tmpMatrix =  new Matrix[String](Field.standartCardHeight, Field.standartFieldWidth, " ")
        hand.zipWithIndex.foreach((elem,index) => tmpMatrix = tmpMatrix.updateMatrixWithMatrix(0, Field.standartSlotWidth * index + 1, hand(index).toMatrix()))
        tmpMatrix
    }

    def toMatrix(): Matrix[String] = new Matrix[String](Field.standartGameBarHeight, Field.standartFieldWidth, " ")
    .updateMatrix(0,0,List[String]("\u001b[32mHP: " + hp.toString + " \u001b[0;34mMana: " + mana.toString + "\u001b[0;37m"))
    .updateMatrix(0,Field.standartFieldWidth - 2, List[String]("\u001b[0;31mDeck: " + deck.length + "  Friedhof: " + friedhof.length + "\u001b[0;37m"))
    .updateMatrixWithMatrix(1,0, handAsMatrix())
    .updateMatrix(6,0,List[String]("-" * Field.standartFieldWidth))
}
