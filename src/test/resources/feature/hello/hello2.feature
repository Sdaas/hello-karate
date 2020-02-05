Feature: Hello Again

  Background:
    Given url 'http://localhost:8080'
    Given path '/api/hello'

  Scenario: Hello Peter

    Given param name = 'Peter'
    When method GET
    Then status 200
    And match $ == "Hello Peter!"