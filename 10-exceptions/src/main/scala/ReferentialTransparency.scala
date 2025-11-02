package cl.uchile.dcc

def failingFn(i: Int): Int =
  try
    val x = 42 + 5
    x + (throw Exception("fail!"))
      .asInstanceOf[Int]
  catch case e: Exception => 43
