import sbt._
import Keys._


object BuildSettings {
  val buildOrganization = "hardware"
  val buildVersion      = "0.1"
  val buildScalaVersion = "2.10.1"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := buildOrganization,
    version      := buildVersion,
    scalaVersion := buildScalaVersion,
    shellPrompt  := ShellPrompt.buildShellPrompt
  )
}

// Shell prompt which show the current project,
// git branch and build version
object ShellPrompt {
  object devnull extends ProcessLogger {
    def info (s: => String) {}
    def error (s: => String) { }
    def buffer[T] (f: => T): T = f
  }
  def currBranch = (
    ("git status -sb" lines_! devnull headOption)
      getOrElse "-" stripPrefix "## "
  )

  val buildShellPrompt = {
    (state: State) => {
      val currProject = Project.extract (state).currentProject.id
      "%s:%s:%s> ".format (
        currProject, currBranch, BuildSettings.buildVersion
      )
    }
  }
}



object Resolvers {
  val sonatype   = "sonatype releases"  at "http://oss.sonatype.org/content/repositories/releases"
  val oracleResolvers = Seq (sonatype)
}

object Dependencies {
  val specs2 = "org.specs2" %% "specs2" % "1.14" % "test"
}


object CDAP2Build extends Build {
  import Resolvers._
  import Dependencies._
  import BuildSettings._

  lazy val main = Project (
     "main",
     file("."),
     settings = buildSettings ++ Seq ( 
     	resolvers := oracleResolvers,
     	libraryDependencies ++= Seq(specs2)
     )
  )

  // lazy val cdap2 = Project (
  //   "cdap2",
  //   file ("."),
  //   settings = buildSettings
  // ) aggregate (common, server, compact, pricing, pricing_service)


  // lazy val server = Project (
  //   "server",
  //   file ("cdap2-server"),
  //   settings = buildSettings ++ Seq (resolvers := oracleResolvers,
  //                                    libraryDependencies ++= serverDeps)
  // ) dependsOn (common)
}


// object MyBuild extends Build {

// 	val appName = "measure"
// 	val appVersion = "0.1"

// 	val mainDependencies = Seq(
//     	"org.specs2" % "specs2" % "1.14" % "test"
//     )

// 	lazy val main = Project("main", file("."))

	
// }