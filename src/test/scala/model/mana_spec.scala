import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.Mana


class ManaSpec extends AnyWordSpec with Matchers {
  "Mana" when {
    "not set any value " should {
      val emptyMana = Mana()
      "have Value 1" in {
        emptyMana.value should be(1)
        emptyMana.max should be(1)
      }
    }
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
}