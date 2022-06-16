//package scala

import model.*
import controller.GameState
import aview.TUI
import aview.GUIApp
import controller.component.controllerImpl.Controller
import model.field_component.fieldImpl.{Field, FieldInterface}

import scala.io.StdIn.readLine
import util.Event
import scalafx.application.Platform

import scala.concurrent.*
import java.time.Duration
import org.scalactic.Bool

import scala.io.StdIn

@main
def run: Unit = {
    val controller = Controller(new Field(5))
    val tui = TUI(controller)
    val GUI = new GUIApp(controller)
    tui.update(Event.PLAY)
    while (controller.gameState != GameState.EXIT) do {
        tui.onInput(StdIn.readLine())
    }


}



