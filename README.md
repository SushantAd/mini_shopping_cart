# mini_shopping_cart
A console based mini shopping cart.

### Problem Statement: Phase 1

#### Shopping cart
You are building a checkout system for a shop which only sells apples and oranges. 
1. Apples cost 60p and oranges cost 25p. 
2. Build a checkout system which takes a list of items scanned at the till and outputs the total cost 
   1. For example: [ Apple, Apple, Orange, Apple ] => £2.05 
   2. Make reasonable assumptions about the inputs to your solution; for example, many
   candidates take a list of strings as input

Suggested Solution:
1. Console based application/Static Input via list of strings e.g inputItems = List("Apple", "Apple", "Orange", "Apple")
   1. Can be extended to be used as an API.
2. Will used mock data models for current implementation, can be further used a actual database models.


Acceptance Criteria:
1. When a user inputs valid items, checkout system will return the total amount of each item per quantity.
2. When a user inputs some valid items and some invalid items:
   1. Checkout system will return the total amount of each *valid* item per quantity.
   2. Checkout system will display list of invalid items.
3. When a user inputs empty item list, checkout system will display no item selected message.


Limitation:
1. Works for only single currency.


### Running
You need to download and install sbt for this application to run.
The input can be changes in Application.scala

```bash
sbt run
```

### Testing
```bash
sbt test
```