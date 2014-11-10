Feature: DocumentService

  Scenario: Add new document
    Given new document with collectionId = 'test'
    When the create endpoint is called
    Then a new record containing recordId is created