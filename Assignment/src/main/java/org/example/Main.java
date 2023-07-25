package org.example;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    private static final String API_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=b6907d289e10d714a6e88b30761fae22";
    private static JSONObject jsonData;
    private static JSONArray jsonArray;
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            jsonData = getWeatherDataFromAPI();
            jsonArray=jsonData.getJSONArray("list");

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
                        String temp = getTemperature(date);
                        if (temp != null) {
                            System.out.println("Temperature at " + date + ": " + temp + " K");
                        } else {
                            System.out.println("No data available for the given date.");
                        }
                        break;
                    case "2":
                        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                        date = br.readLine();
                        String windSpeed = getWindSpeed(date);
                        if (windSpeed != null) {
                            System.out.println("Wind Speed at " + date + ": " + windSpeed + " m/s");
                        } else {
                            System.out.println("No data available for the given date.");
                        }
                        break;
                    case "3":
                        System.out.print("Enter the date (YYYY-MM-DD HH:MM:SS): ");
                        date = br.readLine();
                        String pressure = getPressure(date);
                        if (pressure != null) {
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

    private static JSONObject getWeatherDataFromAPI() throws IOException {
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
            return new JSONObject(response.toString());
        } else {
            return null;
        }
    }

    private static String getTemperature(String date) {
        try{
            for(Object jsonObject:jsonArray){
                JSONObject jsonObject1= (JSONObject) jsonObject;
                if(jsonObject1.get("dt_txt").toString().compareTo(date)==0){
                    JSONObject response= (JSONObject) jsonObject1.get("main");
                    String s=String.valueOf(response.get("temp"));
                    return s;
                }
            }return null;
        }catch(Exception e){
            return null;
        }
    }

    private static String getWindSpeed(String date) {
        System.out.println(date);
        try{
            for(Object jsonObject:jsonArray){
                JSONObject jsonObject1= (JSONObject) jsonObject;
                if(jsonObject1.get("dt_txt").toString().compareTo(date)==0){
                    JSONObject wind= (JSONObject) jsonObject1.get("wind");
                    String s=String.valueOf(wind.get("speed"));
                    return s;
                }
            }
            return null;
        }catch(Exception e){
            return null;
        }

    }

    private static String getPressure(String date) {
        try{
            for(Object jsonObject:jsonArray){
                JSONObject jsonObject1= (JSONObject) jsonObject;
                if(jsonObject1.get("dt_txt").toString().compareTo(date)==0){
                    JSONObject response= (JSONObject) jsonObject1.get("main");
                    String s=String.valueOf(response.get("pressure"));
                    return s;
                }
            }return null;
        }catch(Exception e){
            return null;
        }
    }
}