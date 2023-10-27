package infrastructure.controllers

import application.usecases.{LandMarsRover, OrderCommandToMarsRover, RetrieveMarsRover}
import domain.Command
import infrastructure.dtos.MarsRoverDto
import play.api.mvc._

import java.util.UUID
import javax.inject._

@Singleton
class MarsRoverController @Inject()(
                                     val controllerComponents: ControllerComponents,
                                     val landMarsRover: LandMarsRover,
                                     val orderCommandToMarsRover: OrderCommandToMarsRover,
                                     val retrieveMarsRover: RetrieveMarsRover
                                   ) extends BaseController {
  def land(): Action[AnyContent] = Action {
    val marsRover = landMarsRover.execute()
    Created(MarsRoverDto.from(marsRover).toJson()).as("application/json")
  }

  def retrieveStatus(): Action[AnyContent] = Action { request =>
    val marsRover = retrieveMarsRover.execute(UUID.fromString(request.body.asText.get))
    Ok(MarsRoverDto.from(marsRover).toJson()).as("application/json")
  }

  def orderCommand(): Action[AnyContent] = Action { request =>
    val id: String = request.body.asJson.get("id").as[String]
    val command: String = request.body.asJson.get("command").as[String]
    val marsRover = orderCommandToMarsRover.execute(
      UUID.fromString(id),
      Command(command)
    )
    Ok(MarsRoverDto.from(marsRover).toJson()).as("application/json")
  }
}