package model.card_component.cardImpl

import com.fasterxml.jackson.databind.JsonSerializable
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import model.card_component.CardInterface
import model.card_component.cardImpl.Card
import model.field_component.FieldInterface
import model.field_component.fieldImpl.{Field, FieldObject}
import model.matrix_component.matrixImpl.Matrix
import play.api.libs.json.*

import scala.collection.View.Empty

object Card {
    given cardReads:Reads[Option[Card]] = (o:JsValue) => {
        (o \ "type").validate[String] match
            case JsSuccess("MINION", _) => JsSuccess(Some(Card((o \ "name").as[String].grouped(10).toList(0),(o \ "cost").as[Int], (o \ "attack").as[Int], (o \ "health").as[Int], "Effect", "Rarity")))
            // case JsSuccess("SPELL", _) => JsSuccess(Card("test", 2, 3, 4, "yolo", "ss"))
            // case JsSuccess("HERO_POWER", _) => JsSuccess(Card("test", 2, 3, 4, "yolo", "ss"))
            // case JsSuccess("HERO", _) => JsSuccess(Card("test", 2, 3, 4, "yolo", "ss"))
            // case JsSuccess("ENCHANTMENT", _) => JsSuccess(Card("test", 2, 3, 4, "yolo", "ss"))
            // case JsSuccess("MOVE_MINION_HOVER_TARGET", _) => JsSuccess(Card("test", 2, 3, 4, "yolo", "ss"))
            // case JsSuccess("WEAPON", _) => JsSuccess(Card("test", 2, 3, 4, "yolo", "ss"))
            // case JsSuccess("LETTUCE_ABILITY", _) => JsSuccess(Card("test", 2, 3, 4, "yolo", "ss"))
            case JsSuccess(_, _) => JsSuccess(None)
            case e: JsError => JsSuccess(None)
    }
}
class Card(val name: String, val manaCost: Int, val attValue: Int, val defenseValue: Int, val effect: String, val rarity: String) extends CardInterface {
    override def toString(): String = name + " (" + manaCost + ")" + "#" + "atk: " + attValue + "#def: " + defenseValue + "#" + effect + "#" + rarity
    override def toMatrix(): Matrix[String] = new Matrix[String](FieldObject.standartCardHeight, FieldObject.standartCardWidth, " ").updateMatrix(0, 0, toString().split("#").toList)
}

class EmptyCard(val name: String = "yolo", val manaCost: Int = 0, val attValue: Int = 0, val defenseValue: Int = 0, val effect: String = "", val rarity: String = "") extends CardInterface {
    override def toMatrix(): Matrix[String] = new Matrix[String](FieldObject.standartCardHeight, FieldObject.standartCardWidth, " ")
}
