# Created by Nicolai at 23/03/2026
Feature: tests
  # Enter feature description here

  Scenario: View available users
    Given User1 checks available users
    When User2 is not assigned to any started assignments
    Then User2 should be present on a list over available users.

  Scenario: No available users
    Given User1 checks available users
    When User2 is assigned to a started assignments
    Then User2 should not be present on a list over available users.


  Scenario: Create project with proper project ID
    Given that no other project exists
    And that the year is 2026
    When a user creates a project
    Then a project is created with the project ID "26001"

  Scenario: Alternative to create project with proper project ID
    Given that 100 other project exists
    And  that the year is 2026
    When a user creates a project
    Then a project is created with the project ID "26101"


  Scenario: Assign User
    Given User2 as an input
    And User1 is project leader
    When User2 is not assigned to project
    Then assign User2 to project

  Scenario: Failed to assign User
    Given User2 as an input
    And  User1 is project leader
    When User2 is assigned to project
    Then failed to assign User2 to project ErrorMessage: "User already assigned."


  Scenario: Create assignment
    When a user creates an assignment
    And user is assigned to project
    Then create assignment

  Scenario: fail to create assignment
    When a user creates an assignment
    And user is not assigned project
    Then fail to create assignment


  Scenario: Assign time used
    Given An int 1
    When user assigns time used on assignment
    Then the time 1 is assigned to user

  Scenario: Failed to assign time used
    Given a String "String"
    When user assigns a String as input on an assignment
    Then no time is assigned to user

  Scenario: Check Users Projects
    Given a user wants to fetch their projects
    And User was added to 5 projects
    Then return 5 projects