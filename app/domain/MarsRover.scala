package domain

import java.util.UUID

class MarsRover private (givenId: UUID, initialOrientation: Orientation, initialPosition: Position) {
  val id: UUID = givenId
  var orientation: Orientation = initialOrientation
  var position: Position = initialPosition

  def execute(command: Command): Unit = command.instructions.foreach {
    case 'L' => orientation = orientation.atLeft()
    case 'R' => orientation = orientation.atRight()
    case 'F' => position = position.moveForwardBasedOn(orientation)
  }
}

object MarsRover {
  def land(id: UUID, orientation: Orientation, position: Position) = new MarsRover(id, orientation, position)
}