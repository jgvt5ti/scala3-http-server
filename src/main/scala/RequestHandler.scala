package scala3httpserver

class RequestHandler:
  def requestHandle(request: Request) =
    request match
      case validRequest: ValidRequest     => ()
      case invalidRequest: InvalidRequest => ()
