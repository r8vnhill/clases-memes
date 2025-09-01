package cl.uchile.dcc
package overriding

import scala.collection.mutable

class Person(val name: String, val age: Int, val skills: mutable.Set[Skill]):
  def addSkill(skill: Skill): Unit =
    skills += skill

  override def toString: String =
    s"Person($name, $age, ${skills.mkString(", ")})"

  //noinspection TypeCheckCanBeMatch
  override def equals(obj: Any): Boolean =
    if obj.isInstanceOf[Person] then
      val other = obj.asInstanceOf[Person]
      name == other.name
      && age == other.age
      && skills == other.skills
    else false

  override def hashCode(): Int =
    (classOf[Person], name, age, skills).hashCode()
