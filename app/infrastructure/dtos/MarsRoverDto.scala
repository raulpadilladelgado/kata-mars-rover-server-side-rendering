package infrastructure.dtos

import domain.MarsRover

case class MarsRoverDto(orientation: String, longitude: Int, latitude: Int) {
  def toJson(): String = {
    s"""
       |{
       | "orientation": "$orientation",
       | "longitude": $longitude,
       | "latitude": $latitude
       |}""".stripMargin
  }
}

object MarsRoverDto {
  def from(marsRover: MarsRover): MarsRoverDto = {
    MarsRoverDto(marsRover.orientation.toString, marsRover.position.longitude, marsRover.position.latitude)
  }
}