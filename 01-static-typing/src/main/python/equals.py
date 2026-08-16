def equals(
    a: int,
    b: int,
) -> bool:
    """
    Compara dos enteros e imprime un mensaje cada vez que se ejecuta.

    Este ejemplo permite observar las anotaciones de tipo de una función en Python:

        equals: (int, int) -> bool

    `a: int` y `b: int` indican que esperamos recibir dos enteros, mientras que `-> bool` indica que esperamos producir 
    un valor booleano.

    En Python, estas anotaciones sirven como información para quienes leen el código y para herramientas de análisis 
    estático, pero el runtime no las hace cumplir automáticamente (Python Software Foundation, s. f.).

    La llamada a `print` produce un efecto observable: cada vez que se ejecuta `equals`, se imprime "equals called".

    Finalmente, `a == b` evalúa la igualdad entre ambos argumentos y produce `True` o `False`. La palabra clave 
    `return` devuelve explícitamente ese valor a quien llamó la función.

    Args:
        a: Primer entero que se comparará.
        b: Segundo entero que se comparará.

    Returns:
        `True` si `a` y `b` representan el mismo valor; `False` en caso
        contrario.

    Examples:
        >>> equals(3, 3)
        equals called
        True

        >>> equals(2, 5)
        equals called
        False
    """
    print("equals called")
    return a == b
