import gitbucket.core.controller.Context
import gitbucket.core.plugin.{RenderRequest, Renderer}
import play.twirl.api.Html
import DrawioSettingsService._


class DrawioRenderer extends Renderer with DrawioSettingsService {

  def render(request: RenderRequest): Html = {
    import request._
    Html(toHtml(fileContent)(context))
  }

  def shutdown(): Unit = {
  }

  def toHtml(content: String)(implicit context: Context): String = {
    val path = context.baseUrl
    val settings = loadDrawioSettings()
    var viewer = "";
    var baseUrl = "https://viewer.diagrams.net/"
    if (settings.DrawioBaseUrl != "") {
      viewer = settings.DrawioBaseUrl + "/js/viewer.min.js"
      baseUrl = settings.DrawioBaseUrl
    } else {
      viewer = path + "/plugin-assets/drawio/viewer.min.js"
    }
    val data = content.replaceAll("&", "&amp;")
                      .replaceAll("'", "&#x27;")
                      .replaceAll("`", "&#x60;")
                      .replaceAll("\"", "&quot;")
                      .replaceAll("<", "&lt;")
                      .replaceAll(">", "&gt;")

    s"""
       |<link rel="stylesheet" type="text/css" href="$path/plugin-assets/drawio/drawio-renderer.css">
       |<script>
       |window.DRAWIO_BASE_URL = '$baseUrl';
       |window.DRAWIO_LIGHTBOX_URL = '$baseUrl';
       |window.DRAWIO_VIEWER_URL = '$baseUrl';
       |window.DRAW_MATH_URL = '$baseUrl' + 'math/es5';
       |</script>
       |<script src="$viewer"></script>
       |<script src="$path/plugin-assets/drawio/drawio-renderer.js"></script>
       |<div class="mxgraph" data-diagram-data="$data"></div>
       |""".stripMargin

  }
}
