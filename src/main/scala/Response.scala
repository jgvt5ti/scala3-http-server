package scala3httpserver

import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

enum Status(statusCode: String):
  case Ok extends Status("200 OK")
  case BadRequest extends Status("400 BadRequest")
  case Forbidden extends Status("403 Forbidden")
  case NotFound extends Status("404 NotFound")

case class Response(status: Status, body: Array[Byte]):
  def toBytes: Array[Byte] =
    val response =
      s"""HTTP/1.1 ${status.statusCode}
      |Server: scala3-http-server
      |Content-Type: text/html;charset=utf-8
      |Content-Length: ${body.length.toString}
      |Connection: Close
      |
      """.stripMargin
    val headerBytes = response.getBytes(StandardCharsets.UTF_8)
    val buff = ByteBuffer.allocate(headerBytes.length + body.length)
    buff.put(headerBytes)
    buff.put(body)
    buff.array

object Response:
  val BadRequest = Response(
    Status.BadRequest,
    body = "Bad Request".getBytes(StandardCharsets.UTF_8)
  )
  val Forbidden = Response(
    Status.Forbidden,
    body = "Forbidden".getBytes(StandardCharsets.UTF_8)
  )
  val NotFound = Response(
    Status.NotFound,
    body = "Not Found".getBytes(StandardCharsets.UTF_8)
  )
