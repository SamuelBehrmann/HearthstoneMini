package model.manaComponent.manaImpl

import model.manaComponent.ManaInterface

case class Mana(value: Int = 1, max: Int = 1) extends ManaInterface {

    def increase(amount: Int): Mana = if (amount + value > max) then copy(value = max) else copy(value = value + amount)
    def resetAndIncrease(): Mana = copy(value = max + 1, max = max + 1)
    def decrease(amount: Int): Mana = if (value - amount < 0) then copy(value = 0) else copy(value = value - amount)
    def isEmpty: Boolean = value <= 0
    def setVal(amount: Int): Mana = copy(value = amount, amount)

    override def toString = value.toString
}
