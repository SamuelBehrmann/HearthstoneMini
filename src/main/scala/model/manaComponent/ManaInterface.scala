package model.manaComponent

import model.manaComponent.manaImpl.Mana

trait ManaInterface {
  def increase(amount: Int): Mana 
  def resetAndIncrease(): Mana 
  def decrease(amount: Int): Mana 
  def isEmpty: Boolean
  def setVal(amount: Int): Mana 
}

