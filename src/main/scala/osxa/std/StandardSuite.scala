//     Project: scalajs-osxa
//      Module: lib
// Description: Façade trait for the OS X Automation StandardSuite

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
package osxa.std

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait StandardSuite extends js.Object with StandardAddtions {
  def name(): String

  /**
   * Returns the application's bundle identifier.
   */
  def id(): String

  /**
   * The version of the application.
   */
  def version(): String

  /**
   * Returns true iff the application is currently running.
   */
  def running(): Boolean

  def windows: js.Array[IWindow] = js.native

  var includeStandardAdditions: Boolean = js.native
  //def includeStandardAdditions_=(b: Boolean): Unit = js.native
}
