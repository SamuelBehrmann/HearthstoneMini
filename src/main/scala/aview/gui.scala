package aview

import controller.{Controller, GameState, Strategy}
import model.Card

import scala.io.StdIn.readLine
import scalafx.application.{JFXApp3, Platform}
import scalafx.scene.paint.Color.*
import scalafx.scene.shape.Rectangle
import scalafx.scene.Scene
import scalafx.Includes.*
import util.Event
import util.Observer
import javafx.beans.property.StringProperty
import scalafx.scene.layout.{GridPane, HBox, Pane}
import scalafx.geometry.Insets
import scalafx.scene.text.Text
import scalafx.scene.paint.LinearGradient
import scalafx.scene.paint.Stops
import scalafx.scene.effect.DropShadow
import scalafx.scene.control.{Button, ButtonType, ChoiceDialog, Dialog, Label, RadioButton, TextField}
import scalafx.scene.input.KeyCode.E
import javafx.event.ActionEvent
import javafx.event.EventHandler
import model.{Field, Move}
import scalafx.scene.control.ButtonBar.ButtonData

import scala.io.StdIn
import javafx.collections.ObservableList
import javafx.scene.Node
import javafx.scene.chart.BarChart
import javafx.scene.control.ToggleGroup

import javax.swing.ButtonGroup


class GUI(guiApp: GUIApp, controller: Controller) extends JFXApp3
{
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "HearthstoneMini"
      width = 500
      height = 600

      scene = new Scene {
        fill = White
        Platform.runLater {
          () -> {
            controller.gameState match {
              case GameState.CHOOSEMODE => content = renderModeDialog()
              case GameState.ENTERPLAYERNAMES => content = renderPlayernamesDialog()
              case GameState.MAINGAME => content = renderMainGame()
              case GameState.WIN => stopApp()
            }
          }
        }
      }
    }
  }
  def renderModeDialog() = {
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
        controller.setStrategy(togglegroup.getSelectedToggle.getUserData.asInstanceOf[model.Field])

      addColumn(0, radios(0), radios(1), radios(2))
      add(nextButton1,1,3)
    }
    mainGrid
  }

  def renderMainGame() = {
    val playersGrid = new GridPane() {
      vgap = 20
      padding = Insets(20, 100, 10, 10)
    }

    val player1Grid = new GridPane() {
      vgap = 10
      val gamebar = new GridPane() {
        vgap = 10
        hgap = 10
        val hpBar = new GridPane() {
          val bar = new Rectangle {
            height = 20
            width = (300 * (controller.field.getPlayerById(1).gamebar.hp.value.toDouble / controller.field.getPlayerById(1).gamebar.hp.max.toDouble))
            fill = Green
          }
          val amount = new Label(controller.field.getPlayerById(1).gamebar.hp.value.toString)
          amount.setTextFill(White)
          add(bar, 0,0)
          add(amount,0,0)
        }
        val labelBox = new GridPane{
          val box = new Rectangle{
            height = 20
            width = 50
            fill = White
          }
          val label = new Label(controller.field.getPlayerById(1).name)
          add(box, 0,0)
          add(label,0,0)
        }

        add(labelBox, 0,0)
        add(hpBar, 1,0)
      }
      val fieldbar = new GridPane() {
        vgap = 10
        hgap = 10
      }
      controller.field.getPlayerById(1).fieldbar.cardArea.row.zipWithIndex.map((card) => {
        if (card(0).isDefined) then {
          fieldbar.add(new Rectangle{height = 100; width = 50; fill = Red}, card(1), 0)
        } else {
          fieldbar.add(new Rectangle{height = 100; width = 50; fill = White}, card(1), 0)
        }
      })
      val hand = new GridPane() {
        vgap = 10
        hgap = 10
      }
      controller.field.getPlayerById(1).gamebar.hand.zipWithIndex.map((card) => {
        hand.add(new Rectangle{height = 100; width = 50; fill = Red}, card(1), 0)
      })
      add(gamebar, 0,0)
      add(hand, 0,1)
      add(fieldbar, 0, 2)
    }

    val player2Grid = new GridPane() {
      vgap = 10
      val gamebar = new GridPane() {
        vgap = 10
        hgap = 10
        val hpBar = new GridPane() {
          val bar = new Rectangle {
            height = 20
            width = (300 * (controller.field.getPlayerById(2).gamebar.hp.value.toDouble / controller.field.getPlayerById(2).gamebar.hp.max.toDouble))
            fill = Green
          }
          val amount = new Label(controller.field.getPlayerById(2).gamebar.hp.value.toString)
          amount.setTextFill(White)
          add(bar, 0,0)
          add(amount,0,0)
        }
        val labelBox = new GridPane{
          val box = new Rectangle{
            height = 20
            width = 50
            fill = White
          }
          val label = new Label(controller.field.getPlayerById(2).name)
          add(box, 0,0)
          add(label,0,0)
        }

        add(labelBox, 0,0)
        add(hpBar, 1,0)
      }
      val fieldbar = new GridPane() {
        vgap = 10
        hgap = 10
      }
      controller.field.getPlayerById(2).fieldbar.cardArea.row.zipWithIndex.map((card) => {
        if (card(0).isDefined) then {
          fieldbar.add(new Rectangle{height = 100; width = 50; fill = Red}, card(1), 0)
        } else {
          fieldbar.add(new Rectangle{height = 100; width = 50; fill = White}, card(1), 0)
        }
      })
      val hand = new GridPane() {
        vgap = 10
        hgap = 10
      }
      controller.field.getPlayerById(1).gamebar.hand.zipWithIndex.map((card) => {
        hand.add(new Rectangle{height = 100; width = 50; fill = Red}, card(1), 0)
      })
      add(fieldbar, 0, 0)
      add(hand, 0,1)
      add(gamebar, 0,2)

    }
    playersGrid.add(player1Grid, 0,0)
    playersGrid.add(player2Grid, 0,1)
    playersGrid
  }
  def renderPlayernamesDialog() = {
    val mainGrid = new GridPane() {
      vgap = 10
      hgap = 10
      padding = Insets(20, 100, 10, 10)

      val player1TF = new TextField() {
        promptText = "Player 1"
      }
      val player2TF = new TextField() {
        promptText = "Player 2"
      }
      val nextButton = new Button("next")
      nextButton.disable = true

      nextButton.onMouseClicked = (_) => {
        controller.setPlayerNames(Move(p1 = player1TF.text.value, p2 = player2TF.text.value))
      }
      var bool1 = true
      var bool2 = true
      player1TF.text.onChange { (_, _, newValue) => {
        bool1 = newValue.trim().isEmpty
        nextButton.disable = (bool1 || bool2)
      }}
      player2TF.text.onChange { (_, _, newValue) => {
        bool2 = newValue.trim().isEmpty
        nextButton.disable = (bool1 || bool2)
      }}


      add(new Label("Player 1:"), 0, 0)
      add(player1TF, 1, 0)
      add(new Label("Player 2:"), 0, 1)
      add(player2TF, 1, 1)
      add(nextButton, 1,2)
    }
    mainGrid
  }
}



