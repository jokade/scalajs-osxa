//     Project: Default (Template) Project
//      Module: lib
// Description: Façade trait for the OS X Automation Path object.
package osxa

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName

@js.native
trait Path extends js.Object {
  @JSName("toString")
  def value(): String = js.native
}

object Path {
  def apply(alias: String): Path = js.Dynamic.global.Path(alias).asInstanceOf[Path]
}
