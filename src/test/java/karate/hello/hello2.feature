Feature: Hello Again

  Background:
    * url baseUrl
    * path '/api/hello'

  Scenario: Hello Peter

    * param name = 'Peter'
    * method GET
    * status 200
    * match $ == "Hello Peter!"