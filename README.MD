### Pet Api Back-end Tests 

### Stack of technologies
1. [Java 11](https://www.oracle.com/java/)
2. [Maven](https://maven.apache.org/) - command line test executor
3. [Junit5](https://junit.org/junit5/) - test executor
4. [Allure](http://allure.qatools.ru/) - reports generator
5. [Rest Assured](http://rest-assured.io/) - library for testing RESTful APIs
6. [AssertJ](https://assertj.github.io/doc/) - java library for assertions

### Required installation
1. [Java 11 or higher](https://openjdk.java.net/install/index.html)
2. [IntelliJ IDEA](https://www.jetbrains.com/idea/download)
3. [Allure commandline for generation test report](https://docs.qameta.io/allure/#_installing_a_commandline)
4. [Maven for running tests from command line](https://maven.apache.org/install.html)

### IntelliJ IDEA settings
Project Structure settings:
1. Open File --> New --> Project from Version control --> Git
2. Clone project from the repository by URL ""
3. Open File --> Project Structure --> Project
4. Select project SDK java 11 or higher
5. Select project language level 11 (local variable syntax for lambda parameters)
6. Open File --> Project Structure --> Modules
7. Select language level 11

### Test Execution

#### Run test in one click
Click on play button nearby the desired test. That's all.
Test will be running with default settings.
#### Run smoke tests from command line with default settings
```
mvn clean -DtestTags=smoke test --fail-at-end
```
#### Run regression tests from command line with default settings
```
mvn clean -DtestTags=regression test --fail-at-end
```

#### Run specific test
```
mvn clean -Dtest=FindPetsByStatusGetEndPointsTests#findPetByStatusPositiveScenario test
```

#### Run all tests
```
mvn clean test
```

### Allure test report generation
This is already enough to see the Allure report in one command:
```
allure serve /home/path/to/project/target/allure-results
```
If report generated successfully, report page should be opened automatically in Web Browser:

#####Allure Report Example ::
![Allure Report](src/test/resources/AllureReport.png?raw=true "Allure Report Example")
#####Results can be viewed in graphical view ::
![Allure Graph](src/test/resources/AllureGraph.png?raw=true "Results can be viewed in graphical view")
#####Allure Timeline results can be viewed in Timeline View ::
![Allure TimeLine](src/test/resources/AllureTimeline.png?raw=true "Allure Timeline results can be viewed in Timeline View")
#####Allure Behaviour results can be viewed in Behaviour View ::
![Allure Behaviours](src/test/resources/AllureBehaviours.png?raw=true "Allure Behaviour results can be viewed in Behaviour View")
#####Rest Assured detailed logs can be viewed in test body ::
![Allure RestAssuredLogs](src/test/resources/RestAssuredLogs.png?raw=true "Rest Assured detailed logs can be viewed in test body")
#####Allure results can be viewed in form of Java Packages defined in project ::
![Allure PackageView](src/test/resources/AllurePackageView.png?raw=true "Allure results can be viewed in form of Java Packages defined in project")
#####Allure Results can be linked to Jira UserStory or TestCase as below ::
![Allure Report link to Jira](src/test/resources/JiraConnection.png?raw=true "Allure Results can be linked to Jira UserStory or TestCase as below")
#####Results can be filtered by Pass,Fail,Broken,Ignored statuses ::
![Allure Report with Test Status filters](src/test/resources/AllureFilterByStatus.png?raw=true "Results can be filtered by Pass,Fail,Broken,Ignored statuses")
#####Allure Report Additional Features ::
* Histories and Retries Tabs are also very informative.
* Report also can be downloaded offline with very detailed information by clicking on download button in Allure report.
* Allure Report can be easily integrated with Jenkins.
* Screenshots on failure can be attached for Front-End UserInterface Tests.
* Additional Logs and attachments can be added explicitly apart from failures where ever required. 
* For all the points listed above Allure is better option for Test Reporting.  