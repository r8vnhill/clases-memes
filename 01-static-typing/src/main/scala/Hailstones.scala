/*
 * An implementation of the "Hailstone sequence" (also known as the Collatz sequence).
 *
 * Starting from a positive integer `n`, the sequence is defined as:
 *   - If `n` is even → divide it by 2.
 *   - If `n` is odd  → multiply by 3 and add 1.
 * This process repeats until the value becomes 1.
 *
 * Example (starting with 3):
 *   3 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 */
package cl.uchile.dcc

/**
 * Iterative implementation of the hailstone sequence.
 *
 * Uses a `while` loop to repeatedly update the number until it reaches 1. At each step, the current
 * value is printed.
 *
 * @param n
 *   the starting number of the sequence
 * @return
 *   1 (the final value of the sequence, since all sequences end at 1)
 */
def itHailstone(n: Int): Int =
  var r: Int = n // Start from the initial number
  while r != 1 do // Repeat until we reach 1
    println(r) // Print the current value
    if r % 2 == 0 then // If even, divide by 2
      r = r / 2
    else // If odd, multiply by 3 and add 1
      r = 3 * r + 1
  println(r) // Print the final 1
  r

/**
 * Recursive implementation of the hailstone sequence.
 *
 * This version calls itself instead of using a loop. At each step, the current number is printed.
 *
 * @param n
 *   the current value in the sequence
 * @return
 *   1 (once the recursion reaches the end of the sequence)
 */
//noinspection NoTailRecursionAnnotation
def recHailstone(n: Int): Int =
  if n == 1 then // Base case: stop when n = 1
    println(n)
    n
  else
    println(n)
    if n % 2 == 0 then // If even → recurse with n / 2
      recHailstone(n / 2)
    else // If odd  → recurse with 3 * n + 1
      recHailstone(3 * n + 1)

/**
 * Main program entry point.
 *
 * Runs the hailstone sequence starting from 3, once with the iterative version and once with the
 * recursive version.
 */
@main def hailstones(): Unit =
  itHailstone(3) // Run iterative hailstone
  println("----")
  recHailstone(3) // Run recursive hailstone
