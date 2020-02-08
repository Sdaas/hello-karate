Feature: Hello Again

  Background:
    Given url baseUrl
    Given path '/api/hello'

  Scenario: Hello Peter

    Given param name = 'Peter'
    When method GET
    Then status 200
    And match $ == "Hello Peter!"