Feature: Hotel Booking
  As a user, I want to be able to log in and book a hotel
  So that I can plan my travels

  Scenario Outline: Valid Login and Hotel Booking
    Given I am on the login page
    When I enter valid credentials with username "<username>" and password "<password>"
    And I click on the login button
    Then I should be logged in to my account
    When I search for hotels with "<location>", "<hotel>", "<room_type>", "<number_of_rooms>", "<check_in_date>", "<check_out_date>", "<adults_per_room>", "<children_per_room>"
    And I select a hotel
    And I book the hotel
    Then I should see a confirmation of my booking
    When I click on Logout
    Then I should be logged out of my account

    Examples:
      | username  | password   | location | hotel | room_type | number_of_rooms | check_in_date | check_out_date | adults_per_room | children_per_room |
      | okuhleMru  | FetFle2000!| Sydney   | Hotel Creek | Standard | 1 - One | 28/03/2024 | 30/03/2024 | 2 - Two | 4 - Four |
