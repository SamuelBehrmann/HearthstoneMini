package aview.gui.enter_playernames_screen

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import model.Move
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, Label, RadioButton, TextField, ToggleGroup}

trait EnterPlayernamesScreenInterface extends scalafx.scene.layout.GridPane {
  val textfields: Seq[TextField]
  val labels: Seq[Label]
  val nextButton: Button
  override def add(child: Node, columnIndex: Int, rowIndex: Int, colspan: Int, rowspan: Int): Unit = super.add(child, columnIndex, rowIndex, colspan, rowspan)
}
