Feature: Belly

  Scenario: a few cukes
    Given I have 42 cukes in my belly
    When I wait 1 hour
    Then my belly should growl

  Scenario: a few less cukes
    Given I have 20 cukes in my belly
    When I wait 2 hour
    Then my belly should silent