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
import scalafx.scene
import scalafx.scene.input.MouseEvent
import scalafx.scene.layout.GridPane.getColumnIndex

import javax.swing.ButtonGroup


class GUI(guiApp: GUIApp, controller: Controller) extends JFXApp3
{
  override def start(): Unit = {
    stage = new JFXApp3.PrimaryStage {
      title = "HearthstoneMini"
      width = 700
      height = 600

      scene = new Scene {
        fill = White
        Platform.runLater {
          () -> {
            controller.gameState match {
              case GameState.CHOOSEMODE => content = renderModeselection()
              case GameState.ENTERPLAYERNAMES => content = renderPlayernamesDialog()
              case GameState.MAINGAME => content = renderMainGame()
              case GameState.WIN => stopApp()
            }
          }
        }
      }
    }
  }
  def renderModeselection(): GridPane = {
    val mainGrid: GridPane = new GridPane() {
      vgap = 10
      hgap = 10
      padding = Insets(20, 100, 10, 10)

      val nextButton = new Button("next")
      nextButton.disable = true

      val radios: Seq[RadioButton] = List(new RadioButton("Normal"), new RadioButton("Hardcore"),
        new RadioButton("Debug"))
      radios.head.setUserData(Strategy.normalStrategy())
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
  def renderCard(card: Option[Card]): scene.Node = {
    val background1: Rectangle = new Rectangle() {
      height = 100
      width = 100
      fill = if (card.isDefined) then Grey
      else White
    }

    val mainGrid: GridPane = new GridPane() {
      add(background1, 0,0)
      if (card.isDefined) then {
        val valueGrid: GridPane = new GridPane() {
          val label = new Label(card.get.name)
          val cost = new Label("cost: " + card.get.manaCost.toString)
          val hp = new Label("def: " + card.get.defenseValue.toString)
          val attack = new Label("att: " + card.get.attValue.toString)
          addColumn(0, label, cost, attack, hp)
        }
        add(valueGrid,0,0)
      }
    }
    mainGrid.onDragDetected = (event) => {
      mainGrid.startFullDrag()
    }

    mainGrid.onMouseDragReleased = (event) => {
      val thisNodesX = getColumnIndex(event.getSource.asInstanceOf[Node])
      val thatNodesX = getColumnIndex(event.getGestureSource.asInstanceOf[Node])
      if (event.getSource.asInstanceOf[Node].getParent.getId == "fieldbar" &&
        event.getGestureSource.asInstanceOf[Node].getParent.getId == "hand") then {
        if (event.getSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(0).id.toString &&
          event.getGestureSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(0).id.toString)
        then {
          controller.placeCard(Move(handSlot = thatNodesX, fieldSlotActive = thisNodesX))
        }
      } else if (event.getSource.asInstanceOf[Node].getParent.getId == "fieldbar" &&
        event.getGestureSource.asInstanceOf[Node].getParent.getId == "fieldbar") then {
        if (event.getSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(1).id.toString &&
          event.getGestureSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(0).id.toString)
        then {
          controller.attack(Move(fieldSlotInactive = thisNodesX, fieldSlotActive = thatNodesX))
        }
      }
    }
    mainGrid
  }

  def renderMainGame() = {
    val playersGrid = new GridPane() {
      vgap = 20
      padding = Insets(20, 100, 10, 10)
    }

    val player1Grid = new GridPane() {
      id = "1"
      vgap = 10
      hgap = 10
      var isActive = if (controller.field.players(0).id.toString == id.value) then true else false
      val gamebar = new GridPane() {
        vgap = 10
        hgap = 10
        val hpBar = new GridPane() {
          id = "hpbar"
          val bar = new Rectangle {
            height = 20
            width = (300 * (controller.field.getPlayerById(1).gamebar.hp.value.toDouble / controller.field.getPlayerById(1).gamebar.hp.max.toDouble))
            fill = Green
          }
          val amount = new Label("  " + controller.field.getPlayerById(1).gamebar.hp.value.toString)
          amount.setTextFill(White)
          add(bar, 0,0)
          add(amount,0,0)
        }
        val manaBar = new GridPane() {
          val bar = new Rectangle {
            height = 20
            width = (100 * (controller.field.getPlayerById(1).gamebar.mana.value.toDouble / controller.field.getPlayerById(1).gamebar.mana.max.toDouble))
            fill = Blue
          }
          val amount = new Label("  " + controller.field.getPlayerById(1).gamebar.mana.value.toString)
          amount.setTextFill(White)
          add(bar, 0,0)
          add(amount,0,0)
        }
        hpBar.onMouseDragReleased = (event) => {
          val thatNodesX = getColumnIndex(event.getGestureSource.asInstanceOf[Node])
          println(event.getSource.asInstanceOf[Node].getParent.getParent.getId)
          if (event.getGestureSource.asInstanceOf[Node].getParent.getId == "fieldbar") then {
            if (event.getSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(1).id.toString &&
              event.getGestureSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(0).id.toString)
            then {
              controller.directAttack(Move(fieldSlotActive = thatNodesX))
            }
          }
        }

        val labelBox = new GridPane{
          val box = new Rectangle{
            height = 20
            width = 50
            fill = White
          }
          val label = new Label(controller.field.getPlayerById(1).name)
          label.setTextFill(if isActive then Green else Black)
          add(box, 0,0)
          add(label,0,0)
        }

        add(labelBox, 0,0)
        add(hpBar, 1,0)
        add(manaBar,2,0)
      }
      val fieldbar = new GridPane() {
        gridLinesVisible = true
        id = "fieldbar"
        vgap = 10
        hgap = 10
      }


      controller.field.getPlayerById(1).fieldbar.cardArea.row.zipWithIndex.foreach((card) => {
        fieldbar.add(renderCard(card(0)), card(1), 0)
      })
      val hand = new GridPane() {
        id = "hand"
        vgap = 10
        hgap = 10
      }
      controller.field.getPlayerById(1).gamebar.hand.zipWithIndex.foreach((card) => {
        hand.add(renderCard(Some(card(0))), card(1), 0)
      })
      val deck = new Rectangle() {
        fill = Grey
        height = 100
        width = 100
      }
      deck.onMouseClicked = (_) => {
        if(controller.field.getPlayerById(1).gamebar.hand.length < 5) then controller.drawCard()
      }
      val friedhof = new Rectangle() {
        fill = Red
        height = 100
        width = 100
      }

      add(deck,1,1)
      add(new Label("Deck: " + controller.field.getPlayerById(1).gamebar.deck.length.toString),1,1)
      add(friedhof, 1,2)
      add(new Label("Friedhof: " + controller.field.getPlayerById(1).gamebar.friedhof.length.toString),1,2)
      add(gamebar, 0,0)
      add(hand, 0,1)
      add(fieldbar, 0, 2)

    }

    val player2Grid = new GridPane() {
      id = "2"
      vgap = 10
      hgap = 10
      var isActive = if (controller.field.players(0).id.toString == id.value) then true else false
      val gamebar = new GridPane() {
        vgap = 10
        hgap = 10
        val hpBar = new GridPane() {
          id = "hpbar"
          val bar = new Rectangle {
            height = 20
            width = (300 * (controller.field.getPlayerById(2).gamebar.hp.value.toDouble /
              controller.field.getPlayerById(2).gamebar.hp.max.toDouble))
            fill = Green
          }
          val amount = new Label("  " + controller.field.getPlayerById(2).gamebar.hp.value.toString)
          amount.setTextFill(White)
          add(bar, 0,0)
          add(amount,0,0)
        }

        hpBar.onMouseDragReleased = (event) => {
          val thatNodesX = getColumnIndex(event.getGestureSource.asInstanceOf[Node])
          println(event.getSource.asInstanceOf[Node].getParent.getParent.getId)
          if (event.getGestureSource.asInstanceOf[Node].getParent.getId == "fieldbar") then {
            if (event.getSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(1).id.toString &&
              event.getGestureSource.asInstanceOf[Node].getParent.getParent.getId == controller.field.players(0).id.toString)
            then {
              controller.directAttack(Move(fieldSlotActive = thatNodesX))
            }
          }
        }
        val manaBar = new GridPane() {
          val bar = new Rectangle {
            height = 20
            width = (100 * (controller.field.getPlayerById(2).gamebar.mana.value.toDouble / controller.field.getPlayerById(1).gamebar.mana.max.toDouble))
            fill = Blue
          }
          val amount = new Label("  " + controller.field.getPlayerById(2).gamebar.mana.value.toString)
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
          label.setTextFill(if isActive then Green else Black)
          add(box, 0,0)
          add(label,0,0)
        }

        add(labelBox, 0,0)
        add(hpBar, 1,0)
        add(manaBar,2,0)
      }
      val fieldbar = new GridPane() {
        gridLinesVisible = true
        id = "fieldbar"
        vgap = 10
        hgap = 10
      }
      controller.field.getPlayerById(2).fieldbar.cardArea.row.zipWithIndex.map((card) => {
        if (card(0).isDefined) then {
          fieldbar.add(renderCard(card(0)), card(1), 0)
        } else {
          fieldbar.add(renderCard(card(0)), card(1), 0)
        }
      })
      val hand = new GridPane() {
        id = "hand"
        vgap = 10
        hgap = 10
      }
      controller.field.getPlayerById(2).gamebar.hand.zipWithIndex.map((card) => {
        hand.add(renderCard(Some(card(0))), card(1), 0)
      })
      val deck = new Rectangle() {
        fill = Grey
        height = 100
        width = 100
      }
      deck.onMouseClicked = (_) => {
        if(controller.field.getPlayerById(2).gamebar.hand.length < 5) then controller.drawCard()
      }

      val friedhof = new Rectangle() {
        fill = Red
        height = 100
        width = 100
      }

      add(deck,1,1)
      add(new Label("Deck: " + controller.field.getPlayerById(2).gamebar.deck.length.toString),1,1)
      add(friedhof,1,0)
      add(new Label("Friedhof: " + controller.field.getPlayerById(2).gamebar.friedhof.length.toString),1,0)
      add(fieldbar, 0, 0)
      add(hand, 0,1)
      add(gamebar, 0,2)

    }
    val buttongrid = new GridPane {
      hgap = 10
      val switchButton = new Button("end turn")
      switchButton.onMouseClicked = (_) => controller.switchPlayer()
      val undoButton = new Button("undo")
      undoButton.onMouseClicked = (_) => controller.undo
      val redoButton = new Button("redo")
      redoButton.onMouseClicked = (_) => controller.redo

      add(switchButton,0,2)
      add(undoButton,1,2)
      add(redoButton,2,2)
    }


    playersGrid.add(player1Grid, 0,0)
    playersGrid.add(player2Grid, 0,1)
    playersGrid.add(buttongrid,0,2)
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



