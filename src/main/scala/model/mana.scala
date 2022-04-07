package model

case class Mana(value: Int = 0){
    //val max: Int = 100
    def increase(amount: Int): Mana = copy(value + amount)
    def decrease(amount: Int): Mana = copy(value - amount)
    def isEmpty(): Boolean = value <= 0
    def setVal(amount: Int): Mana = copy(value = amount)

    override def toString() = value.toString()
}
