from typing import Optional


class DatabaseConnection:
    _instance: Optional["DatabaseConnection"] = None

    def __new__(cls) -> "DatabaseConnection":
        if cls._instance is None:
            cls._instance = super(DatabaseConnection, cls).__new__(cls)
            cls._instance.driver = "org.postgresql.Driver"
            cls._instance.url = \
                "jdbc:postgresql://localhost:5432/postgres"
        return cls._instance


if __name__ == "__main__":
    db1 = DatabaseConnection()
    db2 = DatabaseConnection()
    print(db1 is db2) # True
