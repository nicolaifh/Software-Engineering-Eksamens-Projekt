# Created by Nicolai at 23/03/2026
Feature: # Enter feature name here
  # Enter feature description here

  Scenario: View available users
    Given project manager checks available users
    When User1 is not assigned to any started assignments
    Then User1 should be present on a list over available users.

  Scenario: No available users
    Given project manager checks available users
    When User1 is assigned to a started assignments
    Then User1 should not be present on a list over available users.


  Scenario: Create project with proper project ID
    Given that the year is 2026
    And that no other project existes
    When a user creates a project
    Then a project is created with the project ID "26001"

  Scenario: Alternative to create project with proper project ID
    Given that the year is 2026
    And that 100 other project existes
    When a user creates a project
    Then a project is created with the project ID "26101"


  Scenario: Assign User
    Given User2 as an input
    When User2 is not assigned to project
    And User1 is projectleader
    Then assign User2 to project

  Scenario: Failed to assign User
    Given User2 as an input
    When User2 is already assignd to project
    And  User1 is projectleader
    Then faild to assign User2 to project ErrorMessage: "User alleredy assigned."


  Scenario: Create assignment
    When a user creates an assignment
    And user is assigned to project
    Then create assignement

  Scenario: fail to create assignment
    When a user creates an assignment
    And user is not assigned project
    Then fail to create assignement


  Scenario: Assign time used
    Given An int int1
    When user assigns time used on assignment
    Then the time int1 is assigned to user

  Scenario: Failed to assign time used
    Given a String "String"
    When user assigns a String as input on an assignment
    Then no time is assigned to user