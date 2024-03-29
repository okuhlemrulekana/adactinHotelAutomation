Feature: Hotel Booking


  Scenario: Successful Hotel Booking
    Given I am on the login page
    When I enter valid credentials with username and password
    And I click on the login button
    Then I should be logged in to my account
    When I search for hotels with location, hotel, room_type, number_of_rooms, check_in_date, check_out_date, adults_per_room,children_per_room
    And I select a hotel
    And I book the hotel
    Then I should see a confirmation of my booking
    When I click on Logout
    Then I should be logged out of my account

