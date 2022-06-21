package com.mini.shop

import com.mini.shop.repo.ItemRepo
import com.mini.shop.service.CheckoutService

object Application
{
  val defaultAmount: BigDecimal = 0

  def main(args: Array[String]): Unit = {
    /*TBD: Object initialization can later be used via dependency injections*/
    val itemRepo = new ItemRepo
    val checkoutService = new CheckoutService(itemRepo)
    val inputItems = List("Apple", "Apple", "Orange", "Apple")
    inputItems match {
      case Nil => println(s"No items selected")
      case _ => println(s"totalCost = ${checkoutService.checkout(inputItems)}")
    }
  }
}
