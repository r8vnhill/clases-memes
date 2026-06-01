package singleton;

/**
 * Java equivalent of the Scala singleton example used in the design-patterns-2 module.
 * <p>
 * This simplified implementation avoids inner classes and uses a basic lazy
 * initialization with a static field. It is intentionally NOT thread-safe and is
 * meant purely for teaching the Singleton pattern in a single-threaded context.
 */
public final class AppConfig {
    private static AppConfig instance = null; // not thread-safe (see class doc)

    public final String appName;
    public final String theme;

    private AppConfig(String appName, String theme) {
        this.appName = appName;
        this.theme = theme;
    }

    public static AppConfig getInstance() {
        // Not thread-safe: multiple threads could create multiple instances.
        if (instance == null) {
            instance = new AppConfig("CC3002", "light");
        }
        return instance;
    }

    public static void main(String[] args) {
        //noinspection ObjectEquality,ExpressionComparedToItself
        System.out.println(
                AppConfig.getInstance() ==
                        AppConfig.getInstance()
        );
    }
}
