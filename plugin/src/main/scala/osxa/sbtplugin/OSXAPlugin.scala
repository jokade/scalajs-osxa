//     Project: scalajs-osxa
//      Module: plugin
// Description: SBT AutoPlugin for OS X JavaScript Automation scripts written in Scala.js

// Copyright (c) 2016. Distributed under the MIT License (see included LICENSE file).
package osxa.sbtplugin

import sbt._
import sbt.Keys._
import org.scalajs.sbtplugin.ScalaJSPlugin
import org.scalajs.sbtplugin.impl.{DependencyBuilders,ScalaJSGroupID}

object OSXAPlugin extends AutoPlugin {
  import ScalaJSPlugin.AutoImport.{fastOptJS,fullOptJS}

  override def requires: Plugins = ScalaJSPlugin

  object autoImport {
    lazy val osxaFastOpt = taskKey[Attributed[File]]("Compile and fast link OS X Automation JavaScript")
    lazy val osxaFullOpt = taskKey[Attributed[File]]("Compile and optimize OS X Automation JavaScript")
    lazy val osxaRunFast = taskKey[Unit]("Compile, fast link, and execute OS X Automation JavaScript")
  }

  override def projectSettings: Seq[Def.Setting[_]] = Seq(
    autoImport.osxaFastOpt := {
      val file: Attributed[File] = (ScalaJSPlugin.autoImport.fastOptJS in Compile).value
      val lastrunFile = target.value / "osxa.lastrun"
      updateJsFile(file.data,lastrunFile)
      file
    },
    autoImport.osxaFullOpt := {
      val file: Attributed[File] = (ScalaJSPlugin.autoImport.fullOptJS in Compile).value
      val lastrunFile = target.value / "osxa.lastrun"
      updateJsFile(file.data,lastrunFile)
      file
    },
    autoImport.osxaRunFast := {
//      val file: Attributed[File] = (ScalaJSPlugin.autoImport.fastOptJS in Compile).value
      val file = autoImport.osxaFastOpt.value
      runScript(file.data)
    },
    libraryDependencies += DepBuilder.toScalaJSGroupID("de.surfice") %%% "scalajs-osxa" % Version.osxaVersion
  )

  private def updateJsFile(jsFile: File, lastrunFile: File): Unit = {
    if(checkrun(jsFile,lastrunFile))
      IO.append(jsFile,runScript)
    IO.touch(lastrunFile)
  }

  private def runScript(file: File): Int = {
    import sys.process._
    Seq("osascript",file.absolutePath).!
  }

  private def checkrun(file: File, lastrunFile: File): Boolean =
    !lastrunFile.exists() || file.lastModified() > lastrunFile.lastModified()

  private val runScript =
    """if(typeof Init == 'function') {Init();}"""
//    """if(typeof run == 'function') {run();} else {throw 'Cannot execute OSXA script: run() is not defined'};"""

  private object DepBuilder extends DependencyBuilders
}
