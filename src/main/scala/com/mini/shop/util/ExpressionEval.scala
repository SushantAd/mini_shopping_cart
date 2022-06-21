package com.mini.shop.util

import br.com.virsox.scalexpr.ExpressionParser

object ExpressionEval {

  val ctx: Map[String, Any] = Map().empty
  val parser: ExpressionParser = ExpressionParser()

  def resolve(str: String): Boolean={
    val expr = parser.parseBooleanExpression(str).get
    expr.resolve(ctx)
  }

}
