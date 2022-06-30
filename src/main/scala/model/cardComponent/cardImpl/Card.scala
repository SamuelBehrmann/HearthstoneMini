package model.cardComponent.cardImpl

import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import model.cardComponent.CardInterface
import model.cardComponent.cardImpl.Card
import model.fieldComponent.fieldImpl.FieldObject
import model.matrixComponent.matrixImpl.Matrix
import play.api.libs.json.*

import scala.collection.View.Empty
import scala.xml.Node

object Card {
    given cardReads: Reads[Option[Card]] = (o:JsValue) => {
        (o \ "type").validate[String] match
            case JsSuccess("MINION", _) => JsSuccess(Some(Card((o \ "name").as[String].grouped(10).toList.head,
                (o \ "cost").as[Int], (o \ "attack").as[Int], (o \ "health").as[Int],
                "Effect", "Rarity")))
            case JsSuccess(_, _) => JsSuccess(None)
    }
    def fromJSON(json: JsValue): Option[Card] = json.toString() match
        case "none" => None
        case _ => Some(
            Card(
                name = (json \ "name").toString,
                manaCost = (json \ "manaCost").toString.toInt,
                attValue  = (json \ "attValue").toString.toInt,
                defenseValue = (json \ "defenseValue").toString.toInt,
                effect = (json \ "effect").toString,
                rarity = (json \ "rarity").toString
            )
    )

    def fromXML(node: Node) : Option[Card] = node.toString() match
        case "none" => None
        case _ => Some(
            Card(
                name = (node \\ "name").toString(),
                manaCost = (node \\ "manaCost").toString().toInt,
                attValue = (node \\ "attValue").toString().toInt,
                defenseValue = (node \\ "defenseValue").toString().toInt,
                effect = (node \\ "effect").toString(),
                rarity = (node \\ "rarity").toString()
            )
    )
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

    def toJson: JsValue = Json.obj(
        "name" -> name,
        "manaCost" -> manaCost,
        "attValue" -> attValue,
        "defenseValue" -> defenseValue,
        "effect" -> effect,
        "rarity" -> rarity
    )
    def toXML: Node =
        <card>
            <name>{name}</name>
            <manaCost>{manaCost.toString}</manaCost>
            <attValue>{attValue.toString}</attValue>
            <defenseValue>{defenseValue.toString}</defenseValue>
            <effect>{effect}</effect>
            <rarity>{rarity}</rarity>
        </card>
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
