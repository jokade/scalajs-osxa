
lazy val commonSettings = Seq(
  organization := "de.surfice",
  version := "0.1-SNAPSHOT",
  scalaVersion := "2.11.7",
  scalacOptions ++= Seq("-deprecation","-unchecked","-feature","-Xlint"),
  libraryDependencies ++= Seq(
  ),
  scalacOptions ++= (if (isSnapshot.value) Seq.empty else Seq({
        val a = baseDirectory.value.toURI.toString.replaceFirst("[^/]+/?$", "")
        val g = "https://raw.githubusercontent.com/jokade/scalajs-osxa"
        s"-P:scalajs:mapSourceURI:$a->$g/v${version.value}/"
      }))
)


lazy val osxa = project.in(file(".")).
  aggregate(plugin).
  enablePlugins(ScalaJSPlugin).
  settings(commonSettings: _*).
  settings(publishingSettings: _*).
  //settings(sonatypeSettings: _*).
  settings( 
    name := "scalajs-osxa",
    libraryDependencies ++= Seq(
    ),
    resolvers += Resolver.sonatypeRepo("releases")
  )


lazy val plugin = project
  .settings(commonSettings:_*)
  .settings(publishingSettings: _*)
  .settings(
    name := "sbt-osxa",
    description := "sbt plugin for scalajs-osxa (Scala.js façade for OS X Automation)",
    sbtPlugin := true,
    scalaVersion := "2.10.5",
    addSbtPlugin("org.scala-js" % "sbt-scalajs" % "0.6.7"),
    sourceGenerators in Compile += Def.task {
      val file = (sourceManaged in Compile).value / "Version.scala"
      IO.write(file,
        s"""package osxa.sbtplugin
        |object Version { val osxaVersion = "${version.value}" }
        """.stripMargin)
      Seq(file)
    }.taskValue
  )


lazy val publishingSettings = Seq(
  publishMavenStyle := true,
  publishTo := {
    val nexus = "https://oss.sonatype.org/"
    if (isSnapshot.value)
      Some("snapshots" at nexus + "content/repositories/snapshots")
    else
      Some("releases"  at nexus + "service/local/staging/deploy/maven2")
  },
  pomExtra := (
    <url>https://github.com/jokade/scalajs-osxa</url>
    <licenses>
      <license>
        <name>MIT License</name>
        <url>http://www.opensource.org/licenses/mit-license.php</url>
      </license>
    </licenses>
    <scm>
      <url>git@github.com:jokade/scalajs-osxa</url>
      <connection>scm:git:git@github.com:jokade/scalajs-osxa.git</connection>
    </scm>
    <developers>
      <developer>
        <id>jokade</id>
        <name>Johannes Kastner</name>
        <email>jokade@karchedon.de</email>
      </developer>
    </developers>
  )
)
 
