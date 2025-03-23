# cucumber-trello-api

This repository is a result of following cucumber with java rest api course from https://kursy.akademiaqa.pl/cucumber-w-testach-rest-api/

You can run tests with maven and generate allure report after providing your trello auth token in resources.trello.properties file.

To serve test report on local machine make sure you have run tests using `mvn clean test` command before, then use below command:
`allure serve target/allure-results`
