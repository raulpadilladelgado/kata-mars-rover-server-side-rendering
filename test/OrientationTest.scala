import domain.{East, North, South, West}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class OrientationTest extends AnyFunSpec with TableDrivenPropertyChecks with should.Matchers {
  describe("domain.Orientation should be") {
    Table(
      ("label", "initialDirection", "expectedDirection"),
      ("domain.West when looking for left facing domain.North", North(), West()),
      ("domain.North when looking for left facing domain.East", East(), North()),
      ("domain.East when looking for left facing domain.South", South(), East()),
      ("domain.South when looking for left facing domain.West", West(), South())
    ).foreach { case (label, initialOrientation, expectedOrientation) =>
      it(s"$label") {
        initialOrientation.atLeft() should be(expectedOrientation)
      }
    }
    Table(
      ("label", "initialDirection", "expectedDirection"),
      ("domain.East when looking for right facing domain.North", North(), East()),
      ("domain.South when looking for right facing domain.East", East(), South()),
      ("domain.West when looking for right facing domain.South", South(), West()),
      ("domain.North when looking for right facing domain.West", West(), North())
    ).foreach { case (label, initialOrientation, expectedOrientation) =>
      it(s"$label") {
        initialOrientation.atRight() should be(expectedOrientation)
      }
    }
  }
}
