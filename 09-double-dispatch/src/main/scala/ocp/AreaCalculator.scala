package cl.uchile.dcc
package ocp

// totalArea usa single dispatch: invoca shape.area sin saber el tipo.
// Extensible: nuevas figuras pueden añadirse sin cambiar esta función.
def totalArea(shapes: List[Shape]): Double =
  var sum = 0.0
  for shape <- shapes do sum += shape.area
  sum
