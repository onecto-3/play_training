package controllers

import javax.inject.Inject
import javax.inject.Singleton
import play.api.mvc.AbstractController
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api.mvc.ControllerComponents
import play.api.mvc.Request
import play.api.libs.json._
import play.api.libs.functional.syntax._

@Singleton
class PlusController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  
  implicit val rds = (
    (__ \ 'name).read[Long] and
    (__ \ 'age).read[Long]
  ) tupled

  def get(a: Option[Int], b: Option[Int]) =
    Action { implicit request: Request[AnyContent] =>
      Ok {
        if(a.isEmpty || b.isEmpty)
          "Please give arguments of a and b." 
	else
          (a.get+b.get).toString
      }
  }

  def post() = Action(parse.json) { request =>
    val a:JsResult[Long] = request.body.validate[Long]((__ \ 'a).read[Long])
    val b:JsResult[Long] = request.body.validate[Long]((__ \ 'b).read[Long])

    (a , b) match{
      case (x: JsSuccess[Long] , y: JsSuccess[Long]) => Ok(
        Json.toJson(
          Map(
            "sum"->Json.toJson(x.get + y.get)
          )
        )
      )
      case (_,_) => BadRequest("Error")
    }
  }
}

