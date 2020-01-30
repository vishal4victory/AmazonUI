
Amazon Web UI - Search Result Validation Test
==================================

# Introduction

This is a maven repository which automates product search on amazon and validation of the results based on a specific search criteria. 
The automation framework is designed using 
1. page object model pattern
1. Factory pattern for Object initilisation and uses reflection to load the objects
1. Facade pattern for WebDriver initilisation. 
1. Null Object Pattern for Objects initilisation in TestClass
1. uses JAXB class to capture and validate the results. 
1. Uses Extent report for capturing the results. 
1. Captures screenshot in case of failure at any assertion level. 


## Requirements

Supports execution on chrome (79.0.3945.130 ) from mac, java 1.8


# Getting Started

All applications in this repository should be automatically importable, compilable, and runnable.  A good place to start is to try the Basic Playback Sample App.

1. Clone this repository onto your computer or download the zip
1. import to workspace
1. Update the maven project and import dependencies from pom.xml
1. open the testSuite - AmazonUI/testSuite/testng.xml
1. RightCLick the testng.xml and RunAs - TestNG-> with VM arguments 

-Dbrowser="chrome"
-Dplatform="mac"

1. Or execute using mvn command as below

mvn -f pom.xml clean test -DsuiteXmlFile=testSuite/testng.xml -Dbrowser="chrome" -Dplatform="mac"

1. The Testclass has the details of the steps. 
1. Has Extent report for result validation. 
1. Has log4j.Logger implementation for logging on the console


Thank you for reading!