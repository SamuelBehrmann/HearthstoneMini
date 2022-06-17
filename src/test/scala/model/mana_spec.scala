import model.healthpoints_component.hpImpl.Healthpoints
import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.mana_component.manaImpl.Mana


class ManaSpec extends AnyWordSpec with Matchers {
  val testMana = Mana(50,100)
  "Mana" when {
    "value is set" should {
      testMana.setVal(40).value should be (40)
    }
    "Mana decrease" when {
      "initialized with 100" should {
        val setMana = Mana(100, 100)
        "have Value 100" in {
          setMana.value should be(100)
          setMana.max should be(100)
        }
        "have decreased value 0" in {
          val afterAlter = setMana.decrease(100)
          afterAlter.value should be(0)
          afterAlter.max should be(100)
          afterAlter.isEmpty() should be(true)
        }
      }
    }
    "Mana increase" when {
      "initialized with 100" should {
        val setMana = Mana(100, 100)
        "have Value 100" in {
          setMana.value should be(100)
          setMana.max should be(100)
        }
        "have increased value 110" in {
          val afterAlter = setMana.increase(10)
          afterAlter.value should be(100)
          afterAlter.max should be(100)
        }
        "have increased by 1 and reset" in {
          val afterAlter = setMana.resetAndIncrease()
          afterAlter.max should be(setMana.max + 1)
          afterAlter.value should be(setMana.max + 1)
        }
      }
    }
    "when is empty" should {
      testMana.setVal(0).isEmpty() should be (true)
    }
    "when increased" should {
      testMana.increase(20).value should be (70)
    }
  }

}