name := "scala_test"

scalaVersion in ThisBuild := "2.12.1"

lazy val root = project.in(file("."))
  .aggregate(testJVM, testJS)

lazy val test = crossProject.in(file("."))
  .settings(
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.4.4"
  )
  .jsSettings(
    libraryDependencies += "com.github.japgolly.scalajs-react" %%% "core" % "0.11.3",
    jsDependencies ++= Seq(
      "org.webjars.bower" % "react" % "15.3.2"
        / "react-with-addons.js"
        minified "react-with-addons.min.js"
        commonJSName "React",

      "org.webjars.bower" % "react" % "15.3.2"
        / "react-dom.js"
        minified "react-dom.min.js"
        dependsOn "react-with-addons.js"
        commonJSName "ReactDOM",

      "org.webjars.bower" % "react" % "15.3.2"
        / "react-dom-server.js"
        minified "react-dom-server.min.js"
        dependsOn "react-dom.js"
        commonJSName "ReactDOMServer")
  )
  .jvmSettings(
    version := "1.0",
    libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.0.1"
  )

lazy val testJVM = test.jvm
lazy val testJS = test.js