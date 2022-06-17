package scala

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import controller.*
import controller.component.ControllerInterface
import controller.component.controllerImpl.Controller
import model.field_component.FieldInterface
import model.field_component.fieldImpl.Field


class HearthstoneMiniModule extends AbstractModule {
  val defaultSize = 5

  override def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bind(classOf[ControllerInterface]).to(classOf[Controller])
    bind(classOf[FieldInterface]).to(classOf[Field])

  }
}
