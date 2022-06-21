# mini_shopping_cart
Standalone mini shopping cart.

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
2. Will use mock data models for current implementation, can be further be used with an actual database models.

Acceptance Criteria:
1. When a user inputs valid items, checkout system will return the total amount of each item per quantity.
2. When a user inputs some valid items and some invalid items:
   1. Checkout system will return the total amount of each *valid* item per quantity.
   2. Checkout system will display the invalid items.
3. When a user inputs empty item list, checkout system will display no item selected message.

### Problem Statement: Phase 2
#### Shopping cart - Simple Offers
1. The shop decides to introduce two new offers 
   1. buy one, get one free on Apples 
   2. 3 for the price of 2 on Oranges
2. Update your checkout functions accordingly

Suggested Solution:
1. Will currently have 2 kinds of offers: 
   1. freeItems offers (will be applicable for the above criteria)
   2. discount offers (additional offer use case for extensibility)
2. We will have an ItemOffers data model, which will work as a mapping for different offer types and items (products).
3. We can decide the number of offers that can be applicable for an item (Global scope for now).
   1. In case of 2 offers, we choose the offer that is active for the current item.
   2. In case of 2 active offers, we choose the latest offer.
4. Will use mock data models for current implementation, can be further be used with an actual database models.


Acceptance Criteria:
1. When there exists a valid offer for an item, checkout system will apply the appropriate offer.
2. When there exists a valid offer for free items, checkout system will make the additional item free.
3. When there exists a valid offer for discounted items, checkout system will make the return the discounted price on total bill.
4. When there exists no valid offer for an item, checkout system will return the original amount.


Implementation Details:
1. Used external library to evaluate offer condition
2. Used scalexpr (wrapper on top of fastparse)
   1. Reason for using -> Was easier to understand given the current time constraint.
3. Currently used static (hard coded) datasets, would suggest extending to actual database.


### Running
You need to download and install sbt for this application to run.
The input can be changes in Application.scala

```bash
sbt run
or
Run via IDE
```

### Testing
```bash
sbt test
or 
Rum via IDE
```

Limitation:
1. Works for only single currency.
2. Offers are only application for first set of individual items e.g If some buys 4 apples and the offer is 2 at price 1 (It is only applied once).
3. Only single offer can be applied per item (Extention is ready, will have to tweak).


Extensions:
1. Can be moved to a REST API approach.
2. Static models can be updated with Data Models for future use:
Note: Sub-offer tables can be de-normalised into single

Table: Item (Store Item information)
Cols: Id, name, amount

Table: ItemOffers (Item-offer mapping table)
Cols: id, itemId, offerId, offerType, isActive, updatedAt

Table: FreeItemOffer (Stores free item offers e.g 1 Apple free on 2, 5 at the price of 2)
Cols: id, condition, freeItems

Table: DiscountOffer (Stores discount offers e.g 5% off on buying 2 Apples)
Cols: id, condition, discountPercentage
3. Conditional checks can be better optimised.


Current Offers:
1. buy one, get one free on Apples
2. 3 for the price of 2 on Oranges