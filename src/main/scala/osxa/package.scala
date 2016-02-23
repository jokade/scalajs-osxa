//     Project: scalajs-osxa
//      Module: 
// Description: 

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
import scala.scalajs.js

package object osxa {
  def onInit(f: =>Any) = js.Dynamic.global.run = (() => f):js.Function
}
