package model

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.JsonSerializable
import play.api.libs.json._
import scala.collection.View.Empty

trait CardType {
    val name: String
    val manaCost: Int
    val attValue: Int
    val defenseValue: Int
    val effect: String
    val rarity: String
    def toMatrix(): Matrix[String] = new Matrix[String](Field.standartCardHeight, Field.standartCardWidth, " ")
}
object CardType {
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
class Card(val name: String, val manaCost: Int, val attValue: Int, val defenseValue: Int, val effect: String, val rarity: String) extends CardType {
    override def toString(): String = name + " (" + manaCost + ")" + "#" + "atk: " + attValue + "#def: " + defenseValue + "#" + effect + "#" + rarity
    override def toMatrix(): Matrix[String] = new Matrix[String](Field.standartCardHeight, Field.standartCardWidth, " ").updateMatrix(0, 0, toString().split("#").toList)
}

class EmptyCard(val name: String = "yolo", val manaCost: Int = 0, val attValue: Int = 0, val defenseValue: Int = 0, val effect: String = "", val rarity: String = "") extends CardType {
    override def toMatrix(): Matrix[String] = new Matrix[String](Field.standartCardHeight, Field.standartCardWidth, " ")
}
