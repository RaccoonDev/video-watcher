package controllers

import java.io.{File, FileInputStream}

import akka.stream.scaladsl.FileIO
import com.typesafe.config.Config
import play.api.mvc._
import javax.inject._
import play.api._
import play.api.http.HttpEntity
import play.api.libs.concurrent

@Singleton
class VideoController @Inject()(val controllerComponents: ControllerComponents, val config: Config) extends BaseController {

  /*
    ffmpeg command to convert a video file for ipad:
    ffmpeg -i S04E01.mkv -map 0:0 -map 0:2 \
      -vf "scale=-2:720:flags=lanczos" \
      -vcodec libx264 \
      -profile:v main \
      -level 3.1 \
      -preset medium \
      -crf 23 \
      -x264-params ref=4 \
      -c:a:2 copy \
      -movflags +faststart \
      ipad.mp4

   */

  private val folder = config.getString("video.folder")
  private val mediaFormat = config.getString("video.format")
  private val logger = Logger(this.getClass)

  def videoPlayer(filename: String) = Action {
    Ok(views.html.videoPlayer(filename))
  }

  def renderVideo(filename: String) = Action { request =>

    RangeResult.ofFile(new File(s"$folder/$filename"), request.headers.get(RANGE), Some(mediaFormat))

//    logger.info(s"Got request for video: $request")
//    request.headers.get(RANGE) match {
//
//      // response without partial content
//      case None =>
//        val file = new java.io.File(filename)
//        val path = file.toPath
//        val source = FileIO.fromPath(path)
//
//        Result(
//          header = ResponseHeader(200, Map(ACCEPT_RANGES -> "bytes")),
//          body = HttpEntity.Streamed(source, None, Some(mediaFormat))
//        )
//
//      case Some(value) if !value.contains("bytes=") => BadRequest("bad 'Range' header")
//
//      // response with partial content
//      case Some(value) => {
//        val file = new java.io.File(filename)
//
//        val fileLength = file.length()
//        val (start, end) = value.substring("bytes=".length).split("-") match {
//          case x if x.length == 1 => x.head.toLong -> (fileLength - 1)
//          case x => x(0).toLong -> x(1).toLong
//        }
//
//        if (start < 0 || end < 0 || start >= fileLength || end >= fileLength) {
//          Status(REQUESTED_RANGE_NOT_SATISFIABLE).withHeaders(CONTENT_RANGE -> "bytes */%d".format(fileLength))
//        } else {
//
//          val path = file.toPath
//
//          Result(
//            header = ResponseHeader(PARTIAL_CONTENT,
//              Map(
//                CONNECTION -> "keep-alive",
//                ACCEPT_RANGES -> "bytes",
//                CONTENT_RANGE -> "bytes %d-%d/%d".format(start, end, fileLength),
//              )
//            ),
//            body = HttpEntity.Streamed(FileIO.fromPath(path, chunkSize = 8192, startPosition = start), Some(file.getTotalSpace), Some(mediaFormat))
//          )
//        }
//      }
//
//    }

  }

}
