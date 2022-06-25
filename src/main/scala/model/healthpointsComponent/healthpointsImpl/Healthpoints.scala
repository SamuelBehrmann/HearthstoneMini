package model.healthpointsComponent.healthpointsImpl

import model.healthpointsComponent.HealthpointsInterface

case class Healthpoints(value: Int = 0, max: Int = 30)extends HealthpointsInterface {
    def increase(amount: Int): Healthpoints = if (amount + value > max) then copy(value = amount + value, max = amount + value) else copy(value = amount + value)
    def decrease(amount: Int): Healthpoints = if (value - amount < 0) then copy(value = 0) else copy(value = value - amount)
    def isEmpty: Boolean = value <= 0
    def setVal(amount: Int): Healthpoints = copy(value = amount, amount)

    override def toString: String = value.toString
}
