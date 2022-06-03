package aview.gui.modeSelection
import controller.Strategy
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import scalafx.geometry.Insets
import scalafx.scene.Node
import scalafx.scene.control.{Button, RadioButton, ToggleGroup}
import scalafx.scene.layout.GridPane

class mode_selection_impl(delegate: Node) extends Node(delegate: Node) with mode_selection_interface {
  override val radiobuttons: Seq[RadioButton] = List(new RadioButton("Normal"), new RadioButton("Hardcore"),
    new RadioButton("Debug"))
  radiobuttons(0).setUserData(Strategy.normalStrategy())
  radiobuttons(1).setUserData(Strategy.hardcoreStrategy())
  radiobuttons(2).setUserData(Strategy.adminStrategy())
  override val radiogroup: ToggleGroup = new ToggleGroup()
  radiobuttons.foreach(_.setToggleGroup(radiogroup))
  override val nextbutton: Button = new Button("next")

  override def onMouseClicked_=(v: EventHandler[_ >: MouseEvent]): Unit =  controller.setStrategy(radiogroup.getSelectedToggle.getUserData.asInstanceOf[model.Field])
}



val mainGrid = new GridPane() {
  vgap = 10
  hgap = 10
  padding = Insets(20, 100, 10, 10)

  val nextButton = new Button("next")
  nextButton.disable = true

  val radios = List(new RadioButton("Normal"), new RadioButton("Hardcore"),
    new RadioButton("Debug"))
  radios(0).setUserData(Strategy.normalStrategy())
  radios(1).setUserData(Strategy.hardcoreStrategy())
  radios(2).setUserData(Strategy.adminStrategy())

  val togglegroup = new ToggleGroup()
  radios.foreach(_.setToggleGroup(togglegroup))

  val nextButton1 = new Button("next")

  nextButton1.onMouseClicked = (_) =>


  addColumn(0, radios(0), radios(1), radios(2))
  add(nextButton1,1,3)
}
