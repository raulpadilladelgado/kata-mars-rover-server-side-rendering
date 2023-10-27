package infrastructure.dtos

import domain.MarsRover

import java.util.UUID

case class MarsRoverDto(id: UUID, orientation: String, longitude: Int, latitude: Int) {
  def toJson(): String = {
    s"""
       |{
       | "id": "$id",
       | "orientation": "$orientation",
       | "longitude": $longitude,
       | "latitude": $latitude
       |}""".stripMargin
  }
}

object MarsRoverDto {
  def from(marsRover: MarsRover): MarsRoverDto = {
    MarsRoverDto(marsRover.id, marsRover.orientation.toString, marsRover.position.longitude, marsRover.position.latitude)
  }
}