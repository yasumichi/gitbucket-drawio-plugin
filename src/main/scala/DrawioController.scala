import scala.jdk.CollectionConverters._
import scala.sys.process._
import scala.util.Using
import org.eclipse.jgit.api.Git
import gitbucket.core.controller._
import gitbucket.core.service._
import gitbucket.core.util._
import gitbucket.core.util.AdminAuthenticator
import gitbucket.core.util.SyntaxSugars._
import gitbucket.core.util.Directory._
import gitbucket.core.util.JGitUtil._
import org.scalatra.forms._
import DrawioSettingsService._

class DrawioController extends DrawioControllerBase
  with RepositoryService with AccountService
  with ReferrerAuthenticator with AdminAuthenticator
  with DrawioSettingsService

trait DrawioControllerBase extends ControllerBase {
  self: RepositoryService with AccountService with ReferrerAuthenticator with AdminAuthenticator with DrawioSettingsService =>

  val settingsForm: MappingValueType[DrawioSettings] = mapping(
    "DrawioBaseUrl"   -> text(required, maxlength(200))
  )(DrawioSettings.apply)

  get("/admin/drawio")(adminOnly {
    val settings = loadDrawioSettings()
    html.settings(settings.DrawioBaseUrl, None)
  }
  )

  post("/admin/drawio", settingsForm)(adminOnly { form =>
    assert(form.DrawioBaseUrl != null)
    saveDrawioSettings(form)
    html.settings(form.DrawioBaseUrl, Some("Settings Saved"))
  })
}