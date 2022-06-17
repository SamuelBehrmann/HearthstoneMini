package model.fileIOComponent

import model.field_component.fieldImpl.Field


trait fileIOInterface {
  def load: Field
  def safe(field: Field): Unit
}
