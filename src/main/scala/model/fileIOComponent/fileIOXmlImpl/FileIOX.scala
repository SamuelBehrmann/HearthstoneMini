package model.fileIOComponent.fileIOXmlImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import scala.HearthstoneMiniModule
import model.fileIOComponent.fileIOInterface
import model.field_component.fieldImpl.Field

import scala.xml.{ NodeSeq, PrettyPrinter }

class FileIOX extends fileIOInterface {
  override def load: Field = {

  }
}
