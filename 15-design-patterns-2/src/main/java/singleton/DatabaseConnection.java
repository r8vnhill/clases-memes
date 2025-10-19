package singleton;

/**
 * Java equivalent of the Scala singleton example used in the design-patterns-2 module.
 * <p>
 * This simplified implementation avoids inner classes and uses a basic lazy
 * initialization with a static field. It is intentionally NOT thread-safe and is
 * meant purely for teaching the Singleton pattern in a single-threaded context.
 */
public final class DatabaseConnection {
    private static DatabaseConnection instance = null; // not thread-safe (see class doc)

    public final String driver;
    public final String url;

    private DatabaseConnection(String driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public static DatabaseConnection getInstance() {
        // Not thread-safe: multiple threads could create multiple instances.
        if (instance == null) {
            instance = new DatabaseConnection(
                    "org.postgresql.Driver",
                    "jdbc:postgresql://localhost:5432/test"
            );
        }
        return instance;
    }

    public static void main(String[] args) {
        //noinspection ObjectEquality,ExpressionComparedToItself
        System.out.println(
                DatabaseConnection.getInstance() ==
                        DatabaseConnection.getInstance()
        );
    }
}
