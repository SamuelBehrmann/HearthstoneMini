package model.healthpoints_component

import model.healthpoints_component.hpImpl.Healthpoints

trait HealthPointsInterface {
  def increase(amount: Int): Healthpoints 
  def decrease(amount: Int): Healthpoints 
  def isEmpty(): Boolean 
  def setVal(amount: Int): Healthpoints 
}



