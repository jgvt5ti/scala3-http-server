package scala3httpserver

enum Request:
  case ValidRequest(method: String, path: String, version: String)
  case InvalidRequest(rawRequestLine: String)
