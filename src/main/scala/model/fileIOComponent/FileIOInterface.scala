package model.fileIOComponent

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Field


trait FileIOInterface {
  def load: FieldInterface
  def save(field: FieldInterface): Unit
}
