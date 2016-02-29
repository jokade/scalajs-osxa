//     Project: scalajs-osxa
//      Module: lib
// Description: Façade trait for the OS X Automation StandardSuite.Application interface.

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
package osxa.std

import scala.scalajs.js

/**
 * Description: Façade trait for the OS X Automation StandardSuite.Application interface.
 */
@js.native
trait IApplication extends js.Object {
  def name(): String = js.native
}
