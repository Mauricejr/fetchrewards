package controllers

import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerTest
import play.api.test.Helpers.{POST, status}
import play.api.test.{FakeRequest, Injecting}
import play.api.Play.materializer
import play.api.http.HeaderNames
import play.api.libs.json.Json
import play.api.test.Helpers._

class EmailControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {

  val request = FakeRequest(POST, "/api/email").withHeaders(HeaderNames.CONTENT_TYPE -> "application/json")

  val badJson =
    """{
      |    "badEmails": [
      |        "test.email@gmail.com",
      |        "test.email@fetchrewards.com"
      |    ]
      |}""".stripMargin

  val json =
    """{
      |    "emails": [
      |        "test.email@gmail.com",
      |        "test.email@fetchrewards.com"
      |    ]
      |}""".stripMargin

     val json2  = """{
       |    "emails": [
       |        "test.email@gmail.com",
       |        "test.email+spam@gmail.com",
       |        "testemail@gmail.com"
       |    ]
       |}""".stripMargin

  " calling endpoint with invalid json should return BAD_REQUEST " in {
    val home = route(app, request.withBody(Json.parse(badJson))).get
    status(home) mustBe BAD_REQUEST
    contentType(home) mustBe Some("text/plain")
    contentAsString(home) must include("Json value emails does not exists")
  }

  " calling endpoint with valid json with two different emails should return OK 2" in {
    val home = route(app, request.withBody(Json.parse(json))).get
    status(home) mustBe OK
    contentType(home) mustBe Some("text/plain")
    contentAsString(home) must be("2")
  }

  " calling endpoint with valid json with three similar emails should return OK 1" in {
    val home = route(app, request.withBody(Json.parse(json2))).get
    status(home) mustBe OK
    contentType(home) mustBe Some("text/plain")
    contentAsString(home) must be("1")
  }

}
