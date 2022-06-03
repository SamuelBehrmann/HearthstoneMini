package aview.gui.mode_selection_screen
import controller.{Controller, Strategy}
import controller.component.ControllerInterface

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, RadioButton, ToggleGroup}
import scalafx.scene.layout.GridPane

class ModeSelectionScreenImpl(controller: Controller) extends GridPane with ModeSelectionScreenInterface {
  override val radiobuttons: Seq[RadioButton] = List(new RadioButton("Normal"), new RadioButton("Hardcore"),
    new RadioButton("Debug"))
  override val radiogroup: ToggleGroup = new ToggleGroup()
  radiobuttons.foreach(_.setToggleGroup(radiogroup))
  override val nextbutton: Button = new Button("next")
  nextbutton.onMouseClicked = (_) =>
    controller.setStrategy(radiogroup.getSelectedToggle.getUserData.asInstanceOf[model.Field])

  radiobuttons(0).setUserData(Strategy.normalStrategy())
  radiobuttons(1).setUserData(Strategy.hardcoreStrategy())
  radiobuttons(2).setUserData(Strategy.adminStrategy())

  vgap = 10
  hgap = 10
  padding = Insets(20, 100, 10, 10)

  add(radiobuttons(0),0,0)
  add(radiobuttons(1),0,1)
  add(radiobuttons(2),0,2)
  add(nextbutton,1,3)

}
