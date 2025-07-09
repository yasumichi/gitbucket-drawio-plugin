import gitbucket.core.controller.Context
import gitbucket.core.plugin.{RenderRequest, Renderer}
import play.twirl.api.Html

class DrawioRenderer extends Renderer {

  def render(request: RenderRequest): Html = {
    import request._
    Html(toHtml(fileContent)(context))
  }

  def shutdown(): Unit = {
  }

  def toHtml(content: String)(implicit context: Context): String = {
    val path = context.baseUrl
//    val data = content.replaceAll("<.+?>", "")
    val data = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\"", "&quot;")

    s"""
       |<link rel="stylesheet" type="text/css" href="$path/plugin-assets/drawio/drawio-renderer.css">
       |<script src="$path/plugin-assets/drawio/viewer.min.js"></script>
       |<script src="$path/plugin-assets/drawio/drawio-renderer.js"></script>
       |<div class="mxgraph" data-diagram-data="$data"></div>
       |""".stripMargin

  }
}
