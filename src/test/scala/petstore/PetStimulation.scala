package petstore

import scala.concurrent.duration._
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._
import org.checkerframework.checker.units.qual.Length

import scala.language.postfixOps
import scala.util.Random

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

  //** 14/4/ 22 --Code block to use CSV file as input **//

    val csvFeeder = csv("data/inputFile.csv").circular

    def CreatePet() = {
      repeat(20) {
        feed(csvFeeder)
          .exec(http("AddPet_Transaction")
            .post("/v2/pet")
            .headers(headers_0)
            .body(StringBody(
              """
             {
               "id":  ${petid},
               "category": {
                 "id":  ${petid},
                 "name": "string"
               },
               "name":  "${petname}",
               "photoUrls": [
               "string"
               ],
               "tags": [
               {
                 "id":  ${petid},
                 "name": "string"
               }
               ],
               "status": "available"
               }""")).asJson
            .check(status.is(200)))
             /*Exit Test execution if HTTP status is not HTTP 200*/
            .exitHereIfFailed
      }
    }

    private val scn = scenario("PetStimulation")
      .exec(
        CreatePet())

    //**Code block ends here**//

    //** Setup for a Basic Load Scenario**//
    setUp(
      scn.inject(
        //**Injecting a Constant Concurrent Load of 2 users for 10 seconds**//
        constantConcurrentUsers(1).during(10 seconds)
        //atOnceUsers(1)
      )
    ).protocols(httpProtocol)
      .assertions(
        //**Three assertions as per the Requirement*//
        //**Assertion 1: Check that Average Response time doesn't exceed 300 Milliseconds**//
        global.responseTime.mean.lte(300),
        //**Assertion 2: Validate whether all Requests are successful or not**//
        global.successfulRequests.percent.is(100),
        //**Assertion 3: Validate if 20 requests were processed in 10 seconds or not**//
        global.allRequests.count.gte(20)
      )

  }
