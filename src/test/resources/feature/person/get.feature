Feature: Get Tests on /api/person

  Background:
    * url baseUrl
    * def personBase = '/api/person/'

  Scenario: Get non-existent person

    Given path personBase + '123456'
    When method GET
    Then status 404
