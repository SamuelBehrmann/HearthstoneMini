package aview

import javafx.geometry.Side
import javafx.scene.layout.{BackgroundImage, BackgroundPosition, BackgroundRepeat, BackgroundSize}
import scalafx.application.Platform
import scalafx.scene.control.Alert
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.image.Image
import scalafx.scene.layout.Background
import scalafx.scene.paint.Color
import controller.{Controller, GameState}
import util.Observer
import aview.GUI
import util.Event

class GUIApp(val controller:Controller) extends Observer {
    override def update(e: Event) = {
        Platform.runLater{
            e match {
                case Event.EXIT => println("\u001b[2J" + "Schönes Spiel!")
                case Event.PLAY => gui.start()
            }
        }
    }

    val gui: GUI = new GUI(this, controller)
    val thread: Thread = new Thread {
        override def run(): Unit = {
        gui.main(Array())
        }
    }
    thread.start()

    controller.add(this)
}