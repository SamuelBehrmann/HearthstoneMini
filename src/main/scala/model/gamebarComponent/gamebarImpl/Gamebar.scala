package hearthstoneMini
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
import play.api.libs.json.*


import scala.xml.Node
import hearthstoneMini.util.CardProvider
object Gamebar {
    def fromJson(json: JsValue): Gamebar = Gamebar(
        hand = (json \ "hand").validate[List[JsValue]].get.map(card => Card.fromJSON(card).get),
        deck = (json \ "deck").validate[List[JsValue]].get.map(card => Card.fromJSON(card).get),
        friedhof = (json \ "friedhof").validate[List[JsValue]].get.map(card => Card.fromJSON(card).get).toArray,
        hp = Healthpoints.fromJson((json \\ "hp").head),
        mana = Mana.fromJson((json \\ "mana").head)
    )
    def fromXML(node: Node): Gamebar = Gamebar(
        hand = (node \\"hand" \\ "entry").map(card => Card.fromXML(card).get).toList,
        deck = (node \\"deck" \\ "entry").map(card => Card.fromXML(card).get).toList,
        friedhof = (node \\"friedhof" \\ "entry").map(card => Card.fromXML(card).get).toArray,
        hp = Healthpoints.fromXML((node \\ "hp").head),
        mana = Mana.fromXML((node \\ "mana").head)
    )
}
case class Gamebar(hp: Healthpoints = Healthpoints(30, 30),
                   mana: Mana = Mana(),
                   hand: List[Card] = new CardProvider("/json/cards.json").getCards(5),
                   deck: List[Card] = new CardProvider("/json/cards.json").getCards(30),
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
        hand.zipWithIndex.foreach((elem,index) => tmpMatrix = tmpMatrix.updateMatrixWithMatrix(0, FieldObject.standartSlotWidth * index + 1, hand(index).toMatrix))
        tmpMatrix
    }
    def toMatrix: Matrix[String] = new Matrix[String](FieldObject.standartGameBarHeight, FieldObject.standartFieldWidth, " ")
    .updateMatrix(0,0,List[String]("\u001b[32mHP: " + hp.toString + " \u001b[0;34mMana: " + mana.toString + "\u001b[0;37m"))
    .updateMatrix(0,FieldObject.standartFieldWidth - 2, List[String]("\u001b[0;31mDeck: " + deck.length + "  Friedhof: " + friedhof.length + "\u001b[0;37m"))
    .updateMatrixWithMatrix(1,0, handAsMatrix())
    .updateMatrix(6,0,List[String]("-" * FieldObject.standartFieldWidth))
    def toJson: JsValue =
        Json.obj(
            "hp" -> hp.toJson,
            "mana" -> mana.toJson,
            "deck" -> deck.map(card => Json.obj("card" -> card.toJson)),
            "hand" -> hand.map(card => Json.obj("card" -> card.toJson)),
            "friedhof" -> friedhof.map(card => Json.obj("card" -> card.toJson))
        )
    def toXML: Node =
        <Player>
            <hp>{hp.toXML}</hp>
            <mana>{mana.toXML}</mana>
            <hand>{hand.map(card => <entry>{card.toXML}</entry>)}</hand>
            <deck>{deck.map(card => <entry>{card.toXML}</entry>)}</deck>
            <friedhof>{friedhof.map(card => <entry>{card.toXML}</entry>)}</friedhof>
        </Player>
}
