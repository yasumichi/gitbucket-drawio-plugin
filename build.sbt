lazy val root = (project in file(".")).enablePlugins(SbtTwirl)

name := "gitbucket-drawio-plugin"
organization := "io.github.gitbucket"
version := "0.2.0"
scalaVersion := "2.13.7"
gitbucketVersion := "4.36.2"
