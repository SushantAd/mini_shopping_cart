package com.mini.shop.util

import com.mini.shop.models.{DiscountOffer, FreeItemOffer, Item, ItemOffers}

/***
 * Only for testing purpose
 */
object MockDataModels {

  val item1 =  Item(1, "Apple", 0.60)
  val item2 =  Item(2, "Orange", 0.25)
  val item3 =  Item(3, "Grapes", 1.25)

  val items = List(item1, item2, item2)

  val offers = List(
    ItemOffers(1, 1, 1, "freeItem", true, 1655802999016L),
    ItemOffers(2, 1, 1, "discount", false, 1655802999017L),
    ItemOffers(3, 2, 2, "freeItem", true, 1655802999018L),
    ItemOffers(4, 2, 2, "discount", false, 1655802999019L),
    ItemOffers(5, 2, 3, "discount", false, 1655802999020L),
    ItemOffers(6, 5, 3, "discount", true, 1655802999020L),
    ItemOffers(7, 5, 4, "discount", true, 1655802999021L),
    ItemOffers(8, 5, 5, "discount", true, 1655802999022L)
  )

  val freeItems = List(
    FreeItemOffer(1, ">1", 1),
    FreeItemOffer(2, ">2", 1),
    FreeItemOffer(2, ">=2", 1),
  )

  val discounts = List(
    DiscountOffer(1, ">=5", 5.00),
    DiscountOffer(2, ">=3", 2.10),
    DiscountOffer(3, "==1", 1)
  )


}
