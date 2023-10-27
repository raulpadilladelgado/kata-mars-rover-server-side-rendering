package controllers

import application.usecases.{LandMarsRover, SendCommandToMarsRover}
import domain.{Command, MarsRover, North, Position}
import infrastructure.controllers.MarsRoverController
import org.mockito.Mockito.{times, verify, when}
import org.scalatestplus.mockito.MockitoSugar.mock
import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.Play.materializer
import play.api.libs.json.{JsValue, Json}
import play.api.test._
import play.api.test.Helpers._

import java.util.UUID

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class MarsRoverControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  "MarsRoverController should" should {
    val landMarsRover = mock[LandMarsRover]
    when(landMarsRover.execute()).thenAnswer(_ => MarsRover.land(UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3"), North(), Position(0, 0)))
    val sendCommandToMarsRover = mock[SendCommandToMarsRover]
    when(sendCommandToMarsRover.execute(Command("F"))).thenAnswer(_ => MarsRover.land(UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3"), North(), Position(0, 1)))

    "land a Mars Rover" in {
      val controller = new MarsRoverController(
        stubControllerComponents(),
        landMarsRover,
        sendCommandToMarsRover
      )

      val response = controller.land().apply(FakeRequest(POST, "/"))

      status(response) mustBe CREATED
      contentType(response) mustBe Some("application/json")
      val expectedResponse = Json.obj(
        "id" -> "33a4a923-4013-48fa-b408-fcfe3855b1e3",
        "orientation" -> "North",
        "longitude" -> 0,
        "latitude" -> 0
      )
      contentAsJson(response) mustBe expectedResponse
    }

    "retrieve the mars rover status" in {
      val controller = new MarsRoverController(stubControllerComponents(), landMarsRover, sendCommandToMarsRover)
      controller.land().apply(FakeRequest(POST, "/"))

      val response = controller.retrieveStatus().apply(FakeRequest(GET, "/"))

      status(response) mustBe OK
      contentType(response) mustBe Some("application/json")
      val expectedResponse = Json.obj(
        "id" -> "33a4a923-4013-48fa-b408-fcfe3855b1e3",
        "orientation" -> "North",
        "longitude" -> 0,
        "latitude" -> 0
      )
      contentAsJson(response) mustBe expectedResponse
    }

    "order command to the mars rover" in {
      val controller = new MarsRoverController(stubControllerComponents(), landMarsRover, sendCommandToMarsRover)
      controller.land().apply(FakeRequest(POST, "/command"))

      val response = controller.orderCommand().apply(FakeRequest(
        POST,
        "/command"
      ).withTextBody("F"))

      status(response) mustBe OK
      contentType(response) mustBe Some("application/json")
      val expectedResponse = Json.obj(
        "id" -> "33a4a923-4013-48fa-b408-fcfe3855b1e3",
        "orientation" -> "North",
        "longitude" -> 0,
        "latitude" -> 1
      )
      contentAsJson(response) mustBe expectedResponse
    }
  }
}
