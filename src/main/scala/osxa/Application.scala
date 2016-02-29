//     Project: scalajs-osxa
//      Module: lib
// Description: Façade trait for the OSX Automation Application object.

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
package osxa

import osxa.std.StandardSuite

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
@JSName("Application")
object Application extends js.Object {
  def currentApplication(): StandardSuite = js.native
  def apply(name: String): StandardSuite = js.native
}

