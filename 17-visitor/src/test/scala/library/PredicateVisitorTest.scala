package cl.uchile.dcc
package library

import library.predicate.*
import library.visitor.PrettyPrintVisitor

import munit.FunSuite

///** Tests para visitors de predicados (propósito pedagógico). */
//class PredicateVisitorTest extends FunSuite:
//  test("pretty print composite predicate"):
//    val p = new And(
//        new ByName("A"),
//        new Or(new ByYear(2000), new Neg(new ByPrefix("X")))
//    )
//    val pp = new PrettyPrintVisitor
//    val text = pp.pretty(p)
//    assertEquals(text,
//                 "(name == \"A\" AND (year == 2000 OR NOT(startsWith(\"X\"))))"
//    )
