package model.healthpointsComponent

import model.healthpointsComponent.healthpointsImpl.Healthpoints

trait HealthpointsInterface {
  def increase(amount: Int): Healthpoints 
  def decrease(amount: Int): Healthpoints 
  def isEmpty: Boolean
  def setVal(amount: Int): Healthpoints 
}



