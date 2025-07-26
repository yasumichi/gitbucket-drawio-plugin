import java.io.File
import scala.util.Using
import gitbucket.core.util.Directory._
import gitbucket.core.util.SyntaxSugars._
import DrawioSettingsService._

trait DrawioSettingsService {

  val DrawioConf = new File(GitBucketHome, "drawio.conf")

  def  saveDrawioSettings(settings: DrawioSettings): Unit =
    defining(new java.util.Properties()) { props =>
      props.setProperty(DrawioBaseUrl, settings.DrawioBaseUrl)
      Using.resource(new java.io.FileOutputStream(DrawioConf)) { out =>
        props.store(out, null)
      }
    }

  def loadDrawioSettings(): DrawioSettings =
    defining(new java.util.Properties()) { props =>
      if (DrawioConf.exists) {
        Using.resource(new java.io.FileInputStream(DrawioConf)) { in =>
          props.load(in)
        }
      }
      DrawioSettings(
        getValue[String](props, DrawioBaseUrl, "")  
      )
    }
}

object DrawioSettingsService {
  import scala.reflect.ClassTag    
  
  case class DrawioSettings(DrawioBaseUrl: String)

  private val DrawioBaseUrl = "DrawioBaseUrl"

  private def getValue[A: ClassTag](props: java.util.Properties,
                                    key: String,
                                    default: A): A =
    defining(props.getProperty(key)) { value =>
      if (value == null || value.isEmpty) default
      else convertType(value).asInstanceOf[A]
    }

  private def getOptionValue[A: ClassTag](props: java.util.Properties,
                                          key: String,
                                          default: Option[A]): Option[A] =
    defining(props.getProperty(key)) { value =>
      if (value == null || value.isEmpty) default
      else Some(convertType(value)).asInstanceOf[Option[A]]
    }

  private def convertType[A: ClassTag](value: String) =
    defining(implicitly[ClassTag[A]].runtimeClass) { c =>
      if (c == classOf[Boolean]) value.toBoolean
      else if (c == classOf[Int]) value.toInt
      else value
    }
}

