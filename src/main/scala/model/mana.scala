package model

case class Mana(value: Int):
    val max: Int
    def increase(amount: Int): Mana = copy(value = value - amount)
    def decrease(amount: Int): Mana = copy(value = value - amount)
    def isEmpty(): Boolean = value <= 0
    def setVal(amount: Int): Mana = copy(value = amount)