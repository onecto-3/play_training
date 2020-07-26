package controllers

import javax.inject.Inject
import javax.inject.Singleton
import play.api.mvc.AbstractController
import play.api.mvc.Action
import play.api.mvc.AnyContent
import play.api.mvc.ControllerComponents
import play.api.mvc.Request

@Singleton
class PlusController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def get(a: Option[Int], b: Option[Int]) =
    Action { implicit request: Request[AnyContent] =>
      Ok {
        if(a.isEmpty || b.isEmpty)
          "Please give arguments of a and b." 
	else
          (a.get+b.get).toString
      }
  }
}
       
