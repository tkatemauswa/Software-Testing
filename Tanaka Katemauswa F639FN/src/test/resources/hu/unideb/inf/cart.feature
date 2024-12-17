Feature: Saucedemo Shopping Cart

Background:
Given the home page is opened
And a valid user is logged in

Scenario: Remove items from cart
  Given multiple items are added to the cart
  When the 'Remove' button is clicked for an item
  Then the item should be removed from the cart
  And the cart count should decrease

Scenario: Cart persistence after logout
  Given items are added to the cart
  When the user logs out
  And logs back in with the same credentials
  Then the cart should be empty

Scenario: Continue shopping from cart
  Given items are added to the cart
  And the 'Cart' button is clicked
  When the 'Continue Shopping' button is clicked
  Then the inventory page should be displayed
  And the cart items should be preserved