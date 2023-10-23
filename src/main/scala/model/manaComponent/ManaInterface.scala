package hearthstoneMini
package model.manaComponent

import model.manaComponent.manaImpl.Mana
import play.api.libs.json.JsValue

import scala.xml.Node

trait ManaInterface {
  def increase(amount: Int): Mana 
  def resetAndIncrease(): Mana 
  def decrease(amount: Int): Mana 
  def isEmpty: Boolean
  def setVal(amount: Int): Mana
  def toJson: JsValue
  def toXML: Node
}

