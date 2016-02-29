//     Project: scalajs-osxa
//      Module: lib
// Description: Façade trait for the OS X Automation StandardSuite.Window interface.

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
package osxa.std

import scala.scalajs.js

@js.native
trait IWindow extends js.Object {
  def name(): String = js.native
  def id(): Int = js.native
  def visible(): Boolean = js.native
}
