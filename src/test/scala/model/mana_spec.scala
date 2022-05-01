import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.Mana


class ManaSpec extends AnyWordSpec with Matchers {
  "Mana" when {
    "not set any value " should {
      val emptyMana = Mana()
      "have Value 0" in {
        emptyMana.value should be(0)
      }
      "not be set" in {
        emptyMana.isEmpty() should be(true)
      }
    }
  }
  "Mana decrease" when {
    "initialized with 100" should {
      val setMana = Mana(100)
      "have Value 100" in {
        setMana.value should be(100)
      }
      "have decreased value 90" in {
        setMana.decrease(10).value should be(90)
      }
    }
  }
  "Mana increase" when {
    "initialized with 100" should {
      val setMana = Mana(100)
      "have Value 100" in {
        setMana.value should be(100)
      }
      "have increased value 110" in {
        setMana.increase(10).value should be(110)
      }
    }
  }
  "Mana setVal" when {
    "initialized with 100" should {
      val setMana = Mana(100)
      "have Value 100" in {
        setMana.value should be(100)
      }
      "have set value 200" in {
        setMana.setVal(200).value should be(200)
      }
    }
  }
}