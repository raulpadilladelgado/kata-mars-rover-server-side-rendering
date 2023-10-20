package controllers

import domain.{MarsRover, North, Position}
import play.api.libs.json.Json
import play.api.mvc._

import javax.inject._

@Singleton
class MarsRoverController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  def index(): Action[AnyContent] = Action {
    var marsRoverDto = MarsRoverDto.from(MarsRover.land(North(), Position(0, 0)))
    Ok(Json.toJson(marsRoverDto.toJson()))
  }
}

object MarsRoverDto {
  def from(marsRover: MarsRover): MarsRoverDto = {
    MarsRoverDto(marsRover.orientation.toString, marsRover.position)
  }
}

case class MarsRoverDto(orientation: String, position: Position) {
  def toJson(): String = {
    s"""
       |{
       | "orientation": "$orientation"
       | "position": {
       |   "longitude": ${position.longitude},
       |   "latitude": ${position.latitude}
       |  }
       |""".stripMargin
  }
}
