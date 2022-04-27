package scala3httpserver

import java.io.BufferedReader

object RequestRegex:
  final val RequestPattern = "(.+) (.+) (.+)".r

class RequestParser:
  import RequestRegex._
  import Request._
  def parse(in: BufferedReader): Request =
    in.readLine match
      case RequestPattern(method, path, version) =>
        ValidRequest(method, path, version)
      case invalid => InvalidRequest(invalid)
