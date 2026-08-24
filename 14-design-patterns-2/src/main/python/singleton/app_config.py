"""Ejemplo didáctico del patrón Singleton usando ``__new__``."""

from __future__ import annotations

from typing import cast

from typing_extensions import Self


class AppConfig:
    """Configuración global de la aplicación, con una única instancia compartida."""

    _instance: AppConfig | None = None
    app_name: str
    theme: str

    def __new__(cls) -> Self:
        if cls._instance is None:
            instance = super().__new__(cls)
            instance.app_name = "CC3002"
            instance.theme = "light"
            cls._instance = instance
        return cast(Self, cls._instance)


if __name__ == "__main__":
    db1 = AppConfig()
    db2 = AppConfig()
    print(db1 is db2) # True
