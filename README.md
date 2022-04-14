# PetStore Performance Test project using Gatling

## Description

This project utilises Gatling to performance test Swagger Petstore's "Add a new pet" functionality under load

The key project requirements are: 

1. Performance testing API.
  a. Create 20 pets within 10 seconds
  b. Fail the test if more than 1 request is unsuccessful
  c. Fail the test if the average response time is longer than 300ms
  
 2. Performance measure of a business scenario:
  . Implement any other load testing scenarios
  . Perform load testing and analyze the results
  . Suggest improvements for the infrastructure/application under test


## Getting Started

### Assertions implemented in Gatling

In order to validate the 3 key requirements (a.,b.,c.) above, I have implemented 3 assertions in the Gatling Script:

a.Gatling Code Block for Assertions: 

![image](https://user-images.githubusercontent.com/100013508/163298102-986d2676-c3c3-42de-a20b-5287fb643e17.png)

b.Assertions reported in the Gatling Performance Test Summary Report: 

![image](https://user-images.githubusercontent.com/100013508/163298164-e1716134-a6ab-453e-a64a-da9a3ecce7a7.png)

c. Additionally, in order to cater for requirement b., I have implemented validaton to exit test if any request is unsuccessful, i.e. not HTTP 200: 

![image](https://user-images.githubusercontent.com/100013508/163298315-b7b47e57-8ac7-4fc4-9d8c-4ed811251a6c.png)


### Installing

The software installation and pre-requisite steps are required in order to run the Gatling load test: 
1. Performance Testing Tool: Gatling OpenSource installation - Please download from: https://gatling.io/thank-you/
2. IDE for Development & Testing: IntelliJ IDEA Community Edition https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC
3. Build Automation: Maven: https://maven.apache.org/download.cgi
4. Setup Gatling project using Maven Archetype and Opening Project in IntelliJ - https://sunilsk1.medium.com/quick-start-your-gatling-journey-with-maven-cabffff28b53


### Executing program

Once IntelliJ setup is completed as per installation instructions and the project file has been opened in the IDE, please follow the following 
execution instructions: 
1. Click Run --> Run Engine menu option in IntelliJ as below: 

![image](https://user-images.githubusercontent.com/100013508/163297161-336fc1aa-aa1a-4b03-ad49-8eae9b61724e.png)

2. Enter a Run Description when prompted as per screenshot below: 

![image](https://user-images.githubusercontent.com/100013508/163297357-4f63d4a2-aee2-47cc-bdbc-669d196b315d.png)

3. Once the Test script has been successfully executed, Gatling will prompt to open the HTML results file as below: 

![image](https://user-images.githubusercontent.com/100013508/163297505-adf5c8c7-bbef-4e2b-b5d1-47a077f65be4.png)

4. Open the Folder location and find index.html Gatling Test Report file as below: 

![image](https://user-images.githubusercontent.com/100013508/163297651-d774dc12-3593-4a0a-a002-030a8153f0c0.png)

5. Gatling's Performance Test Summary Report would be as below: 

![image](https://user-images.githubusercontent.com/100013508/163297758-4f1b7bcb-f14b-46cf-b8ac-8288cb1a3fe7.png)

## Help

Any advise for common problems or issues.
```
command to run if program contains helper info
```

## Authors

Contributor's names and contact info

Harinder Singh
harinder83@gmail.com

## Version History

* 0.1
    * Initial Release

