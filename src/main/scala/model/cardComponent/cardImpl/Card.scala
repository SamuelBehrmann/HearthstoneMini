package model.cardComponent.cardImpl

import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import model.cardComponent.CardInterface
import model.cardComponent.cardImpl.Card
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.{Field, FieldObject}
import model.matrixComponent.matrixImpl.Matrix
import play.api.libs.json.*

import scala.annotation.nowarn
import scala.collection.View.Empty

object Card {
    @nowarn
    given cardReads: Reads[Option[Card]] = (o:JsValue) => {
        (o \ "type").validate[String] match
            case JsSuccess("MINION", _) => JsSuccess(Some(Card((o \ "name").as[String].grouped(10).toList.head,
                (o \ "cost").as[Int], (o \ "attack").as[Int], (o \ "health").as[Int],
                "Effect", "Rarity")))
            case JsSuccess(_, _) => JsSuccess(None)

    }
}
case class Card(val name: String,
           val manaCost: Int, val attValue: Int, val defenseValue: Int,
           val effect: String, val rarity: String,
           var attackCount: Int  = 1)
  extends CardInterface {
    override def toString: String = name + " (" + manaCost + ")" + "#" + "atk: " + attValue + "#def: "
      + defenseValue + "#" + effect + "#" + rarity
    override def toMatrix: Matrix[String] = new Matrix[String](FieldObject.standartCardHeight,
        FieldObject.standartCardWidth, " ").updateMatrix(0, 0,
        toString().split("#").toList)
    override def reduceHP(amount: Int): Card = copy(defenseValue = defenseValue - amount)
    override def reduceAttackCount(): Card = copy(attackCount = attackCount - 1)
    override def resetAttackCount(): Card = copy(attackCount = 1)
}

case class EmptyCard(val name: String = "yolo", val manaCost: Int = 0,
                val attValue: Int = 0, val defenseValue: Int = 0, val effect: String = "", val rarity: String = "",
                var attackCount: Int = 0)
  extends CardInterface {
    override def toMatrix: Matrix[String] = new Matrix[String](FieldObject.standartCardHeight,
        FieldObject.standartCardWidth, " ")

    override def reduceHP(amount: Int): EmptyCard = copy(defenseValue = defenseValue - amount)
    override def reduceAttackCount(): EmptyCard = copy(attackCount = attackCount - 1)
    override def resetAttackCount(): CardInterface = copy(attackCount = 0)
}
