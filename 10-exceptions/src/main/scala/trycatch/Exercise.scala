package cl.uchile.dcc
package trycatch

class BaseException extends Exception
class DerivedException extends BaseException

@main def example1(): Unit =
  try throw new BaseException
  catch
    case e: DerivedException => println("DerivedException")
    case e: BaseException    => println("BaseException")

@main def example2(): Unit =
  try throw new BaseException
  catch
    case e: BaseException    => println("BaseException")
    case e: DerivedException => println("DerivedException")

@main def example3(): Unit =
  try throw new DerivedException
  catch
    case e: DerivedException => println("DerivedException")
    case e: BaseException    => println("BaseException")

@main def example4(): Unit =
  try throw new DerivedException
  catch
    case e: BaseException    => println("BaseException")
    case e: DerivedException => println("DerivedException")

@main def example5(): Unit =
  try throw new DerivedException
  catch
    case e: DerivedException => throw e
    case e: BaseException    => println("BaseException")
