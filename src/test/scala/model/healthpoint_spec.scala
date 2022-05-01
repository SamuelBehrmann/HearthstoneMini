package model

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers
import model.Healthpoints

class HpSpec extends AnyWordSpec with Matchers {
  "HP" when {
    "not set any value " should {
      val emptyHp = Healthpoints()
      "have Value 0" in {
        emptyHp.value should be(0)
      }
      "not be set" in {
        emptyHp.isEmpty() should be(true)
      }
    }
  }
  "HP decrease" when {
    "initialized with 100" should {
      val setHp = Healthpoints(100)
      "have Value 100" in {
        setHp.value should be(100)
      }
      "have decreased value 90" in {
        setHp.decrease(10).value should be(90)
      }
    }
  }
  "HP increase" when {
    "initialized with 100" should {
      val setHp = Healthpoints(100)
      "have Value 100" in {
        setHp.value should be(100)
      }
      "have increased value 110" in {
        setHp.increase(10).value should be(110)
      }
    }
  }
  "HP setVal" when {
    "initialized with 100" should {
      val setHp = Healthpoints(100)
      "have Value 100" in {
        setHp.value should be(100)
      }
      "have set value 200" in {
        setHp.setVal(200).value should be(200)
      }
    }
  }
}