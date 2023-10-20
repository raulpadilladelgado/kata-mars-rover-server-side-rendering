import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should
import org.scalatest.prop.TableDrivenPropertyChecks

class OrientationTest extends AnyFunSpec with TableDrivenPropertyChecks with should.Matchers {
  describe("Orientation should be") {
    Table(
      ("label", "initialDirection", "expectedDirection"),
      ("West when looking for left facing North", North(), West()),
      ("North when looking for left facing East", East(), North()),
      ("East when looking for left facing South", South(), East()),
      ("South when looking for left facing West", West(), South())
    ).foreach { case (label, initialOrientation, expectedOrientation) =>
      it(s"$label") {
        initialOrientation.atLeft() should be(expectedOrientation)
      }
    }
    Table(
      ("label", "initialDirection", "expectedDirection"),
      ("East when looking for right facing North", North(), East()),
      ("South when looking for right facing East", East(), South()),
      ("West when looking for right facing South", South(), West()),
      ("North when looking for right facing West", West(), North())
    ).foreach { case (label, initialOrientation, expectedOrientation) =>
      it(s"$label") {
        initialOrientation.atRight() should be(expectedOrientation)
      }
    }
  }
}
