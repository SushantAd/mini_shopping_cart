package com.mini.shop

import com.mini.shop.repo.{ItemRepo, OfferRepo}
import com.mini.shop.service.CheckoutService

object Application
{
  def main(args: Array[String]): Unit = {
    /*TBD: Object initialization can later be used via dependency injections*/
    val itemRepo = new ItemRepo
    val offerRepo = new OfferRepo
    val checkoutService = new CheckoutService(itemRepo, offerRepo)
    val inputItems = List("Apple", "Apple", "Orange", "Apple")
    inputItems match {
      case Nil => println(s"No items selected")
      case _ => println(s"totalCost = ${checkoutService.checkout(inputItems)}")
    }
  }
}
