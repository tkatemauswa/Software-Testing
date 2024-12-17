Feature: Saucedemo Product Sorting

Background:
Given the home page is opened
And a valid user is logged in

Scenario: Sort products by price low to high
  When the sort dropdown is set to 'Price (low to high)'
  Then the products should be displayed in ascending price order

Scenario: Sort products by price high to low
  When the sort dropdown is set to 'Price (high to low)'
  Then the products should be displayed in descending price order

Scenario: Sort products alphabetically
  When the sort dropdown is set to 'Name (A to Z)'
  Then the products should be displayed in alphabetical order