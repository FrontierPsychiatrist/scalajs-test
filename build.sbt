name := "scala_test"

lazy val commonSettings = Seq(
  scalaVersion := "2.11.8"
)

lazy val root = project.in(file("."))
  .settings(commonSettings: _*)
  .aggregate(client, server)

lazy val client = crossProject
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    version := "1.0"
  )
  .jsSettings(
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.11.1",
    jsDependencies ++= Seq(
      "org.webjars.bower" % "react" % "15.0.2"
        / "react-with-addons.js"
        minified "react-with-addons.min.js"
        commonJSName "React",

      "org.webjars.bower" % "react" % "15.0.2"
        / "react-dom.js"
        minified "react-dom.min.js"
        dependsOn "react-with-addons.js"
        commonJSName "ReactDOM",

      "org.webjars.bower" % "react" % "15.0.2"
        / "react-dom-server.js"
        minified "react-dom-server.min.js"
        dependsOn "react-dom.js"
        commonJSName "ReactDOMServer")
  ).js

lazy val server = project
  .settings(commonSettings: _*)
  .settings(
    version := "1.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-http-core" % "2.4.4"
  )