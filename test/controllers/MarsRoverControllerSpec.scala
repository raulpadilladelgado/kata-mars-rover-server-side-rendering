package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.libs.json.Json
import play.api.test._
import play.api.test.Helpers._

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class MarsRoverControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "MarsRoverController should" should {
    "land a Mars Rover" in {
      val controller = new MarsRoverController(stubControllerComponents())
      val home = controller.retrieveStatus().apply(FakeRequest(POST, "/"))

      status(home) mustBe OK
    }

    "retrieve mars rover status" in {
      val controller = new MarsRoverController(stubControllerComponents())
      val home = controller.retrieveStatus().apply(FakeRequest(GET, "/"))

      status(home) mustBe OK
      contentType(home) mustBe Some("application/json")
      contentAsJson(home).prettifier mustBe Json.toJson(
        """{
          | "orientation": "North",
          | "position": {
          |   "longitude": 0,
          |   "latitude": 0
          | }
          |}""".stripMargin
      ).prettifier
    }
  }
}
