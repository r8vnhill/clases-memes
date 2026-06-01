from __future__ import annotations
from typing import Optional


class AppConfig:
    _instance: Optional[AppConfig] = None
    appName: str
    theme: str
    
    def __new__(cls) -> AppConfig:
        if cls._instance is None:
            cls._instance = super(AppConfig, cls).__new__(cls)
            cls._instance.appName = "CC3002"
            cls._instance.theme = "light"
        return cls._instance


if __name__ == "__main__":
    db1 = AppConfig()
    db2 = AppConfig()
    print(db1 is db2) # True
