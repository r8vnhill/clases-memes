package cl.uchile.dcc
package library

import library.model.*
import library.predicate.*

import munit.FunSuite

import scala.collection.mutable

class LibraryTest extends FunSuite:
  private var library: Library = new Library
  private val book1 = new Book("El libro Troll", 2015)
  private val book2 = new Book("The Witcher", 2010)
  private val game1 = new SteamGame("The Witcher", 2015)

  override def beforeEach(context: BeforeEach): Unit =
    library = new Library
    library.add(book1)
    library.add(book2)
    library.add(game1)

  test("empty Library"):
    val emptyLibrary = new Library
    assert(emptyLibrary.isEmpty)

  test("non empty Library"):
    assert(!library.isEmpty)

  test("search by name"):
    val expected: mutable.Set[Item] = mutable.Set[Item](book1)
    assertEquals(library.searchByPredicate(new ByName("El libro Troll")),
                 expected
    )

  test("search by name not found"):
    val expected: mutable.Set[Item] = mutable.Set.empty[Item]
    assertEquals(library.searchByPredicate(new ByName("Spiderman")), expected)

  test("search by name multiple items"):
    val expected: mutable.Set[Item] = mutable.Set[Item](book2, game1)
    assertEquals(library.searchByPredicate(new ByName("The Witcher")), expected)

//  test("search by year"):
//    val expected: mutable.Set[Item] = mutable.Set[Item](book1, game1)
//    assertEquals(library.searchByPredicate(new ByYear(2015)), expected)
//
//  test("search by prefix"):
//    val expected: mutable.Set[Item] = mutable.Set[Item](book2, game1)
//    assertEquals(library.searchByPredicate(new ByPrefix("The")), expected)
//
//  test("search by and"):
//    val expected: mutable.Set[Item] = mutable.Set[Item](game1)
//    assertEquals(
//        library.searchByPredicate(
//            new And(new ByName("The Witcher"), new ByYear(2015))
//        ),
//        expected
//    )
//
//  test("search by or"):
//    val expectedOr: mutable.Set[Item] = mutable.Set[Item](book1, book2, game1)
//    assertEquals(
//        library.searchByPredicate(
//            new Or(new ByName("The Witcher"), new ByYear(2015))
//        ),
//        expectedOr
//    )
//
//    val expectedAnd: mutable.Set[Item] = mutable.Set[Item](book1)
//    assertEquals(
//        library.searchByPredicate(
//            new And(new Or(new ByName("The Witcher"), new ByYear(2015)),
//                    new ByPrefix("E")
//            )
//        ),
//        expectedAnd
//    )
//
//  test("negation"):
//    val expectedNegated: mutable.Set[Item] = mutable.Set.empty[Item]
//    assertEquals(
//        library.searchByPredicate(
//            new Neg(new Or(new ByName("The Witcher"), new ByYear(2015)))
//        ),
//        expectedNegated
//    )
//
//    val expectedPositive: mutable.Set[Item] = mutable.Set[Item](book2)
//    assertEquals(library.searchByPredicate(new Neg(new ByYear(2015))),
//                 expectedPositive
//    )
//
//  test("search by game"):
//    val expected: mutable.Set[Item] = mutable.Set(game1)
//    assertEquals(library.searchByPredicate(new ByVideoGame), expected)
