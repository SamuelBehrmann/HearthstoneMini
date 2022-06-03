package aview.gui.modeSelection

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import scalafx.scene.control.{Button, RadioButton, ToggleGroup}

trait mode_selection_interface extends scalafx.scene.Node {
  val radiogroup: ToggleGroup
  val radiobuttons: Seq[RadioButton]
  val nextbutton: Button

  override def onMouseClicked_=(v: EventHandler[_ >: MouseEvent]): Unit = super.onMouseClicked_=(v)

}
