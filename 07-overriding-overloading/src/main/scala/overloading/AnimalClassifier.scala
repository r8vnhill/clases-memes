package cl.uchile.dcc
package overloading

object AnimalClassifier:
  def classify(dog: Dog): String =
    "Dog"
  def classify(cat: Cat): String =
    "Cat"
  def classify(animal: Animal): String =
    "Unknown animal"

@main def testClassifier(): Unit =
  val animals: List[Animal] = List(
      new Cat("Alvina"),
      new Dog("Koromaru"),
      new Dog("Amaterasu")
  )
  for animal: Animal <- animals do
    println(
        AnimalClassifier
          .classify(animal)
    )
