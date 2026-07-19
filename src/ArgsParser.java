import java.util.HashMap;

class ArgsParser {
    private HashMap<String, String> values = new HashMap<>();

    public ArgsParser(String[] args) {
        for (String arg : args) {
            if (arg.startsWith("--") && arg.contains("=")) {
                String[] parts = arg.substring(2).split("=", 2);
                values.put(parts[0], parts[1]);
            }
        }
    }

    public int getInt(String key, int defaultValue) {
        if (values.containsKey(key)) {
            return Integer.parseInt(values.get(key));
        }
        return defaultValue;
    }
}