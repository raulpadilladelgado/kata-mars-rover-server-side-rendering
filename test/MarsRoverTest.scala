
import domain.{Command, MarsRover, North, Position, South}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

import java.util.UUID

class MarsRoverTest extends AnyFunSpec with TableDrivenPropertyChecks with should.Matchers {
  describe("Mars Rover") {
    it("should be landed facing given specific orientation and location") {
      val marsRover = MarsRover.from(UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3"), North(), Position(0, 0))

      marsRover.orientation should be(North())
      marsRover.position should be(Position(0, 0))
    }

    it("should rotate given directions") {
      val marsRover = MarsRover.from(UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3"), North(), Position(0, 0))

      marsRover.execute(Command("LLLR"))

      marsRover.orientation should be(South())
    }

    it("should move forward considering current orientation and position") {
      val marsRover = MarsRover.from(UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3"), North(), Position(0, 0))

      marsRover.execute(Command("FFF"))

      marsRover.position should be(Position(0, 3))
    }
  }
}
