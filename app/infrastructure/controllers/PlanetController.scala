package infrastructure.controllers

import domain.{Command, MarsRover, Position, South}
import play.api.data.Form
import play.api.data.Forms.{mapping, text}
import play.api.mvc._
import views.html.main

import java.util.UUID
import javax.inject._

case class MyFormData(command: String)

@Singleton
class PlanetController @Inject()(
                                  val controllerComponents: ControllerComponents,
                                ) extends BaseController {
  val marsRover: MarsRover = MarsRover.from(UUID.randomUUID(), South(), Position(0, 0))

  def retrieveRover(): Action[AnyContent] = Action {
    Ok(main.apply(marsRover))
  }

  def orderCommandToRover(): Action[AnyContent] = Action { implicit request =>
    commandForm.bindFromRequest().fold(
      formWithErrors => {
        BadRequest("Bad request")
      },
      formData => {
        marsRover.execute(Command(formData.command))
        Ok(main.apply(marsRover))
      }
    )
  }

  private val commandForm: Form[MyFormData] = Form(
    mapping(
      "command" -> text,
    )(MyFormData.apply)(MyFormData.unapply)
  )
}