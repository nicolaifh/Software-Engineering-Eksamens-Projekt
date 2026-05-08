# Created by Nicolai at 23/03/2026
Feature: tests
  # Enter feature description here

  # TDD tests

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


# White-Box tests
  # 1
  Scenario: Crete project where ProjectsPerYear does not contain Year
    Given that no projects in the year 2026 exist
    And the current year is 2026
    When User1 creates a project
    Then ProjectsPerYear contains 2026 as key and value ["26001"]

  Scenario: Crete project where ProjectsPerYear contains Year
    Given a project in the year 2026 exist
    And the current year is 2026
    When User1 creates a project
    Then ProjectsPerYear contains 2026 as key and value ["26001", "26002"]

  # 2
  Scenario: User is not assigned to project
    Given a User1 that is assigned to 0 projects
    Then return empty arrayList of projects

  Scenario: User is assigned to project
    Given a User1 that is assigned to 1 projects
    Then return arrayList with assigned project

  # 3
  Scenario: The assignment is started
    Given the only User1 that is assighed to the only assignment in the project
    And the assignment is startet 
    Then return empty arrayList of available useres

  Scenario: The assignment is not started
    Given the only User1 that is assighed to the only assignment in the project
    And the assignment is not startet 
    Then return arrayList containing said user

  # 4
  Scenario: Project is not assigned user as projectLead
    Given a User1 with the acceslevel 0
    And a project whitout a projectLead
    When User1 is assgned projectLead
    Then the project reamins without a projectLead

  Scenario: Project is assigned user as projectLead
    Given a User1 with the acceslevel 1
    And a project whitout a projectLead
    When User1 is assgned projectLead
    Then the project is given User1 as projectLead
  
  # 5
  Scenario: Assignment has no startDate
    Given an assignment whitout a startDate
    When a user checks if the assignment has started
    Then return "false"

  Scenario: Assignment is started
    Given an assignment whit a startDate before the curent date
    When a user checks if the assignment has started
    Then return "true"

  Scenario: Assignment is not started
    Given an assignment whit a startDate after the curent date
    When a user checks if the assignment has started
    Then return "false"

