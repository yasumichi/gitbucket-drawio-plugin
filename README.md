# gitbucket-drawio-plugin (Forked version)

A GitBucket plugin for rendering Draw.io file.

Based on [Diagram renderer](https://github.com/laingsimon/render-diagram).

## Screenshot

![screenshot](https://github.com/onukura/gitbucket-drawio-plugin/raw/assets/screenshot.png)

## Install

1. Download *.jar from Releases.
2. Deploy it to `GITBUCKET_HOME/plugins`.
3. Restart GitBucket.

## Build from source

```sbt
sbt assembly
```

This makes the assembly package
`target/scala-2.13/gitbucket-drawio-plugin-{plugin-version}.jar`
for deployment.

## Usage

This plugin process files with `.drawio` extension.

## Settings

You can modify it to use self-hosted draw.io.

<img width="1022" height="371" alt="draw.io settings" src="https://github.com/user-attachments/assets/28448405-6d44-40c0-b2c4-1e8ea9a68cc7" />

## Version

Plugin version|GitBucket version
:---|:---
0.1.x |4.36.x -
0.2.x |4.36.x -
