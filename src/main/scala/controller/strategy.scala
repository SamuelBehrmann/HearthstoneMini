package controller

import model.field_component.fieldImpl.Field
import model.field_component.FieldInterface

object Strategy {
    def normalStrategy(): FieldInterface = Field().setHpValues(30).setManaValues(1)
    def hardcoreStrategy(): FieldInterface = Field().setHpValues(10).setManaValues(10)
    def adminStrategy(): FieldInterface = Field().setHpValues(100).setManaValues(100)
}
