package model

case class Healthpoints(value: Int = 0) {
    val max: Int = 100
    def increase(amount: Int): Healthpoints = copy(value + amount)
    def decrease(amount: Int): Healthpoints = copy(value - amount)
    def isEmpty(): Boolean = value <= 0
    def setVal(amount: Int): Healthpoints = copy(value = amount)
}
