//     Project: scalajs-osxa
//      Module:
// Description: Façade trait for the OSX Automation Application object.

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
package osxa

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
@JSName("Application")
object Application extends js.Object {
  def currentApplication(): StandardSuite = js.native
}

@js.native
trait StandardSuite extends js.Object {
  def Application: IApplication = js.native
}

@js.native
trait IApplication extends js.Object {
  def name: String = js.native
}
