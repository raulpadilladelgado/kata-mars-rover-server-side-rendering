package infrastructure.controllers

import domain.{MarsRover, Position, South}
import play.api.mvc._
import views.html.main

import java.util.UUID
import javax.inject._

@Singleton
class PlanetController @Inject()(
                                  val controllerComponents: ControllerComponents,
                                ) extends BaseController {
  def retrieve(): Action[AnyContent] = Action {
    Ok(main.render(MarsRover.from(UUID.randomUUID(), South(), Position(0, 0))))
  }
}