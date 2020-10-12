package controllers

import javax.inject._
import play.api.mvc._
import javax.inject.Singleton
import play.api.libs.json.JsResultException
import services.EmailService
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.libs.json.Reads._
import scala.concurrent.Future

@Singleton
class EmailController @Inject()(val email: EmailService, val controllerComponents: ControllerComponents) extends BaseController {
  def findUniqueEmail:Action[AnyContent] = Action.async { implicit request =>

    val resultEmail = request.body.asJson match {
      case Some(json) =>
        try {
          (json \ "emails").as[List[String]]

        } catch {
          case e: JsResultException => println(s"Invalid Json ${e.toString}")
            List.empty[String]
        }

      case None => List.empty[String]
    }
    resultEmail match {
      case Nil => Future(BadRequest("Json value emails does not exists"))
      case _ =>
        val result = email.countUniqueEmails(resultEmail)
        result.map(x =>
          Ok(x.toString)
        )
    }
  }
}