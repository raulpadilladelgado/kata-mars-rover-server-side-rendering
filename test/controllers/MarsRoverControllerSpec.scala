package controllers

import application.usecases.{LandMarsRover, RetrieveMarsRover, OrderCommandToMarsRover}
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

/*
* ✅"land a Mars Rover"
* ✅"retrieve the mars rover status"
* ✅"order command to the mars rover"
* "respond bad request when mars rover is not found retrieving its status"
* "respond bad request when mars rover is not found ordering command to it"
* "respond bad request when mars rover cannot landing to a position"
* "respond bad request when mars rover cannot move to a position"
* */

class MarsRoverControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "MarsRoverController should" should {
    "land a Mars Rover" in {
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
      controller.land().apply(FakeRequest(POST, "/"))

      val response = controller.retrieveStatus().apply(
        FakeRequest(GET, "/").withTextBody("33a4a923-4013-48fa-b408-fcfe3855b1e3")
      )

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
      controller.land().apply(FakeRequest(POST, "/command"))

      val response = controller.orderCommand().apply(FakeRequest(
        POST,
        "/command"
      ).withJsonBody(
        Json.obj("command" -> "FFRFLF", "id" -> "33a4a923-4013-48fa-b408-fcfe3855b1e3"))
      )

      status(response) mustBe OK
      contentType(response) mustBe Some("application/json")
      val expectedResponse = Json.obj(
        "id" -> "33a4a923-4013-48fa-b408-fcfe3855b1e3",
        "orientation" -> "North",
        "longitude" -> 1,
        "latitude" -> 3
      )
      contentAsJson(response) mustBe expectedResponse
    }
  }

  val landMarsRover: LandMarsRover = {
    val landMarsRover = mock[LandMarsRover]
    when(landMarsRover.execute()).thenAnswer(_ => MarsRover.from(UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3"), North(), Position(0, 0)))
    landMarsRover
  }

  val retrieveMarsRover: RetrieveMarsRover = {
    val retrieveMarsRover = mock[RetrieveMarsRover]
    when(retrieveMarsRover.execute(
      UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3")
    )).thenAnswer(_ => MarsRover.from(UUID.fromString("33a4a923-4013-48fa-b408-fcfe3855b1e3"), North(), Position(0, 0)))
    retrieveMarsRover
  }

  val controller: MarsRoverController = new MarsRoverController(
    stubControllerComponents(),
    landMarsRover,
    retrieveMarsRover
  )
}
