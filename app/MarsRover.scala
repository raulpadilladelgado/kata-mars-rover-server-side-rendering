class MarsRover (initialOrientation: Orientation, initialPosition: Position) {
  var orientation: Orientation = initialOrientation
  var position: Position = initialPosition

  def execute(command: Command): Unit = command.instructions.foreach {
    case 'L' => orientation = orientation.atLeft()
    case 'R' => orientation = orientation.atRight()
    case 'F' => position = position.moveForwardBasedOn(orientation)
  }
}

object MarsRover {
  def land(orientation: Orientation, position: Position) = new MarsRover(orientation, position)
}