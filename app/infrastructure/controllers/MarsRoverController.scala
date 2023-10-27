package infrastructure.controllers

import application.usecases.{LandMarsRover, SendCommandToMarsRover}
import domain.{Command, MarsRover, North, Position}
import infrastructure.dtos.MarsRoverDto
import play.api.mvc.{Action, _}

import javax.inject._

@Singleton
class MarsRoverController @Inject()(
                                     val controllerComponents: ControllerComponents,
                                     val landMarsRover: LandMarsRover,
                                     val sendCommandToMarsRover: SendCommandToMarsRover
                                   ) extends BaseController {
  private var marsRover: MarsRover = None.orNull

  def land(): Action[AnyContent] = Action {
    marsRover = landMarsRover.execute()
    Created(MarsRoverDto.from(marsRover).toJson()).as("application/json")
  }

  def retrieveStatus(): Action[AnyContent] = Action {
    Ok(MarsRoverDto.from(marsRover).toJson()).as("application/json")
  }

  def orderCommand(): Action[AnyContent] = Action { request =>
    marsRover = sendCommandToMarsRover.execute(Command(request.body.asText.get))
    Ok(MarsRoverDto.from(marsRover).toJson()).as("application/json")
  }
}