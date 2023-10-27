package application.usecases

import domain.{Command, MarsRover}

import java.util.UUID

class OrderCommandToMarsRover {
  def execute(id: UUID, command: Command): MarsRover = ???

}
