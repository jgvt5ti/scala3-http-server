package scala3httpserver

import java.nio.file.{Files, Paths}
import Response._
import Request._

object RequestHandler:
  def handleRequeset(request: Request): Response =
    request match
      case validRequest: ValidRequest =>
        val normalizedPublicPath =
          Paths.get("public", validRequest.path).normalize
        val path =
          if (Files.isDirectory(normalizedPublicPath))
            normalizedPublicPath.resolve("index.html")
          else normalizedPublicPath
        if (!path.startsWith("public/"))
          Response.Forbidden
        else if (!Files.exists(path))
          Response.NotFound
        else
          Response(Status.Ok, Files.readAllBytes(path))

      case invalidRequest: InvalidRequest => Response.BadRequest
