package aview.gui.mode_selection_screen

import javafx.event.EventHandler
import scalafx.scene.Node
import javafx.scene.input.MouseEvent
import scalafx.scene.control.{Button, RadioButton, ToggleGroup}

trait ModeSelectionScreenInterface extends scalafx.scene.layout.GridPane {
  val radiogroup: ToggleGroup
  val radiobuttons: Seq[RadioButton]
  val nextbutton: Button

  override def add(child: Node, columnIndex: Int, rowIndex: Int, colspan: Int, rowspan: Int): Unit = super.add(child, columnIndex, rowIndex, colspan, rowspan)
}
