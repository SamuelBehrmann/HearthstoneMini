package model

import scala.compiletime.ops.string
import scala.collection.View.Empty

case class GameBar(hp: Healthpoints = new Healthpoints(100),
    mana: Mana = new Mana(0),
    hand: Array[CardType] = Array[CardType](new EmptyCard(), new EmptyCard(), new EmptyCard(),new EmptyCard(), new EmptyCard()),
    deck: Array[CardType] = Array[CardType](new Card("Brecher", 2, 3, 4, "Truemmer", "Legende"), EmptyCard(), EmptyCard(), EmptyCard(), EmptyCard())) {

    val eol = sys.props("line.separator")
    def removeCardFromHand(card: CardType): GameBar = copy(hand = hand.filter(_ != card))
    def addCardToHand(card: CardType): GameBar = copy(hand = hand.appended(card))
    def reduceHp(amount: Int): GameBar = copy(hp = new Healthpoints(hp.value - amount))
    def increaseHp(amount: Int): GameBar = copy(hp = new Healthpoints(hp.value + amount))
    def reduceMana(amount: Int): GameBar = copy(hp = new Healthpoints(mana.value - amount))
    def increaseMana(amount: Int): GameBar = copy(hp = new Healthpoints(mana.value + amount))
    def drawCard(): GameBar = copy(hand = hand.appended(deck(0)),deck = deck.filter(_ != deck(0)))

    def handAsMatrix(): Matrix[String] = new Matrix[String](Field.standartCardHeight, Field.standartFieldWidth, " ")
    .updateMatrixWithMatrix(0, Field.standartSlotWidth * 0 + 1, hand(0).toMatrix())
    .updateMatrixWithMatrix(0, Field.standartSlotWidth * 1 + 1, hand(1).toMatrix())
    .updateMatrixWithMatrix(0, Field.standartSlotWidth * 2 + 1, hand(2).toMatrix())
    .updateMatrixWithMatrix(0, Field.standartSlotWidth * 3 + 1, hand(3).toMatrix())
    .updateMatrixWithMatrix(0, Field.standartSlotWidth * 4 + 1, hand(4).toMatrix())

    def toMatrix(): Matrix[String] = new Matrix[String](Field.standartGameBarHeight, Field.standartFieldWidth, " ")
    .updateMatrix(0,0,List[String]("\u001b[32;1mHP: " + hp.toString + " \u001b[0;34mMana: " + mana.toString + "\u001b[0;37m"))
    .updateMatrixWithMatrix(1,0, handAsMatrix())
    .updateMatrix(6,0,List[String]("-" * Field.standartFieldWidth))
}
