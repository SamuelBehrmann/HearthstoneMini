package model.fileIOComponent

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldImpl.Field

//hello
trait FileIOInterface {
  def load: FieldInterface
  def save(field: FieldInterface): Unit
}
