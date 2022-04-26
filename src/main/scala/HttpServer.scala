import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import scala.util.{Try, Using}
import scala.concurrent._
import ExecutionContext.Implicits.global

object HttpServer:
  val port = 80

  @main def main: Unit =
    val serverSocket = ServerSocket(port)
    while (true)
      val clientSocket = serverSocket.accept
      Future {
        Using(clientSocket) { socket =>
          val in = BufferedReader(InputStreamReader(socket.getInputStream))
          val out = BufferedWriter(OutputStreamWriter(socket.getOutputStream))
          val str = in.readLine
          println(str)
          out.write("HTTP/1.1 200 OK\r\n")
          out.write("Content-Type: text/html\r\n\r\n");
          out.write("<h1>Hello World!!</h1>");
          out.flush
        }
      }
