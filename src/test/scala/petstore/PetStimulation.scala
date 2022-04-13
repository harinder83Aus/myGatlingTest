package petstore

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import scala.language.postfixOps

class PetStimulation extends Simulation {

  //*HTTP Configuration Section*//

  private val httpProtocol = http
    .baseUrl("https://petstore.swagger.io")
    .acceptHeader("application/json")
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .contentTypeHeader("application/json")
    .originHeader("https://petstore.swagger.io")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:99.0) Gecko/20100101 Firefox/99.0")

  //*Headers Section*//
  private val headers_0 = Map(
    "Sec-Fetch-Dest" -> "empty",
    "Sec-Fetch-Mode" -> "cors",
    "Sec-Fetch-Site" -> "same-origin",
    "TE" -> "trailers"
  )

  //*Pet Stimulation Scenario Definition*//

  private val scn = scenario("PetStimulation")
    .exec(
      http("AddPet_Transaction")
        .post("/v2/pet")
        .headers(headers_0)
        .body(RawFileBody("C:\\Users\\harinder.singh\\myGatlingTest\\src\\test\\resources/0000_request.json"))
        //Check HTTP Server Response
        .check(status.is(200))
    )

  //** Setup for a Basic Load Scenario**//
  setUp(
    scn.inject(
    //**Injecting a Constant Concurrent Load of 10 users for 1 minute**//
      constantConcurrentUsers(10).during(1 minutes)
      //atOnceUsers(1)
    )
  ).protocols(httpProtocol)
    .assertions(
      //Assertion to check that Max Response time doesn't exceed 300 Milliseconds
      global.responseTime.max.lt(300),
      //Assertion to validate if all Requests are successful or not
      global.successfulRequests.percent.is(100),
      //Assertion to validate if 120 requests were processed in a minute. i.e. 20 requests in 10 seconds
      global.allRequests.count.is(120)
     )

}
