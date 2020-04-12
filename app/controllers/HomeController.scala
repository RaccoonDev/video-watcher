package controllers

import java.io.File

import com.typesafe.config.Config
import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents, val config: Config) extends BaseController {

  val videoFolder: String = config.getString("video.folder")

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.index(new File(videoFolder).listFiles().filter(f => f.isFile() && f.getName().split("\\.").last.equals("mp4")).map(_.getName).sorted.toList))
  }
}
