package controllers

import domain.{MarsRover, North, Position}
import play.api.libs.json.{JsValue, Json}
import play.api.mvc._
import play.libs.Json

import javax.inject._

@Singleton
class MarsRoverController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {
  var marsRover: Option[MarsRover] = None

  def land(): Action[AnyContent] = Action { implicit request =>
    MarsRoverDto.fromJson(request.body.asJson.get)
    var marsRover = MarsRover.land(North(), Position(0, 0))
    Ok
  }

  def retrieveStatus(): Action[AnyContent] = Action {
    val marsRoverDto = MarsRoverDto.from(MarsRover.land(North(), Position(0, 0)))
    Ok(Json.toJson(marsRoverDto.toJson()))
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

object MarsRoverDto {
  def from(marsRover: MarsRover): MarsRoverDto = {
    MarsRoverDto(marsRover.orientation.toString, marsRover.position)
  }

  def fromJson(json: JsValue): MarsRoverDto = {
    return MarsRoverDto(json["orientation"], json["position"])
  }
}