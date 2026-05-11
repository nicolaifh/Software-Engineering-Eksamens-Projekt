# Created by Nicolai at 23/03/2026
Feature: tests

  Scenario: View available users
    Given User checks available users
    When User is not assigned to any started activitys
    Then User should be present on a list over available users.

  Scenario: No available users
    Given User checks available users
    When User is assigned to a started activitys
    Then User should not be present on a list over available users.


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
    When User is assigned to project
    Then failed to assign user to project ErrorMessage: "User already assigned."


  Scenario: Create activity
    When a user creates an activity
    And user is assigned to a project
    Then create activity

  Scenario: fail to create activity
    When a user creates an activity
    And user is not assigned a project
    Then fail to create activity


  Scenario: Assign time used
    Given An int 1
    When user assigns time used on activity
    Then the time 1 is assigned to user

  Scenario: Failed to assign time used
    Given a String "String"
    When user assigns a String as input on an activity
    Then no time is assigned to user


# Other tests
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
  Scenario: The activity is started
    Given the only User1 that is assighed to the only activity in the project
    And the activity is startet 
    Then return empty arrayList of available useres

  Scenario: The activity is not started
    Given the only User1 that is assighed to the only activity in the project
    And the activity is not startet 
    Then return arrayList containing user

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
  Scenario: Registered time is between 0 and 48 half hours.
    Given a User1 that has 4 hours registered for an activity
    When User registers 4 hours for the activity
    Then the time used for user in activity is 8 hours

  Scenario: Registered time is over 48 half hours.
    Given a User1 that has 42 hours registered for an activity
    When User registers 8 hours for the activity
    Then the exeption "IllegalArgumentException" is trown for User1 registering 8 houres

  Scenario: Registered time is under 0 half hours.
    Given a User1 that has 6 hours registered for an activity
    When User registers -10 hours for the activity
    Then the exeption "IllegalArgumentException" is trown for User1 registering -10 houres