package scala3httpserver

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.ServerSocket
import scala.util.{Try, Using}
import scala.concurrent._
import ExecutionContext.Implicits.global
import RequestParser._
import RequestHandler._

object HttpServer:
  val port = 80

  @main def main: Unit =
    val serverSocket = ServerSocket(port)
    while (true)
      val clientSocket = serverSocket.accept
      Future {
        Using(clientSocket) { socket =>
          val in = BufferedReader(InputStreamReader(socket.getInputStream))
          val out = socket.getOutputStream
          val request = RequestParser.parse(in)
          val response = RequestHandler.handleRequeset(request)
          val bytes: Array[Byte] = response.toBytes
          out.write(bytes)
          out.flush
        }
      }
