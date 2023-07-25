import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private static final String API_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
    private static String jsonData;
    private static String jsObject;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
             jsonData = getWeatherDataFromAPI();

            //System.out.println(jsonData);
            if (jsonData == null) {
                System.out.println("Error fetching weather data.");
                return;
            }

            while (true) {
                System.out.println("\nOptions:");
                System.out.println("1. Get weather");
                System.out.println("2. Get Wind Speed");
                System.out.println("3. Get Pressure");
                System.out.println("0. Exit");

                System.out.print("Enter your choice (0-3): ");
                String choice = br.readLine();

                switch (choice) {
                    case "0":
                        System.out.println("Terminating the program.");
                        return;
                    case "1":
                        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                        String date = br.readLine();
                        double temp = getTemperature(jsonData, date);
                        if (temp != -1) {
                            System.out.println("Temperature at " + date + ": " + temp + " K");
                        } else {
                            System.out.println("No data available for the given date.");
                        }
                        break;
                    case "2":
                        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                        date = br.readLine();
                        double windSpeed = getWindSpeed(jsonData, date);
                        if (windSpeed != -1) {
                            System.out.println("Wind Speed at " + date + ": " + windSpeed + " m/s");
                        } else {
                            System.out.println("No data available for the given date.");
                        }
                        break;
                    case "3":
                        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                        date = br.readLine();
                        double pressure = getPressure(jsonData, date);
                        if (pressure != -1) {
                            System.out.println("Pressure at " + date + ": " + pressure + " hPa");
                        } else {
                            System.out.println("No data available for the given date.");
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getWeatherDataFromAPI() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            return response.toString();
        } else {
            return null;
        }
    }
    private static JsonObject getWeatherDataFromAPI() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            JsonParser parser = new JsonParser();
            JsonElement rootElement = parser.parse(br);
            return rootElement.getAsJsonObject();
        } else {
            return null;
        }
    }
    private static double getTemperature(String jsonData, String date) {
        // Parse the JSON data and extract the temperature for the given date
        // Implement the parsing logic here based on the JSON structure
        // Return the temperature value or -1 if not found
        // For simplicity, I'm returning -1 here
        return -1;
    }

    private static double getWindSpeed(String jsonData, String date) {
        // Parse the JSON data and extract the wind speed for the given date
        // Implement the parsing logic here based on the JSON structure
        // Return the wind speed value or -1 if not found
        // For simplicity, I'm returning -1 here
        return -1;
    }

    private static double getPressure(String jsonData, String date) {
        // Parse the JSON data and extract the pressure for the given date
        // Implement the parsing logic here based on the JSON structure
        // Return the pressure value or -1 if not found
        // For simplicity, I'm returning -1 here
        return -1;
    }
}