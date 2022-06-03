package controller
import model.Field
object Strategy {
    def normalStrategy(): Field = Field().setHpValues(30)
    def hardcoreStrategy(): Field = Field().setHpValues(10).setManaValues(10)
    def adminStrategy(): Field = Field().setHpValues(100).setManaValues(100)
}
