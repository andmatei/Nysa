package nysa.nysa_20.model;

public class LocationDataKeeper {
    private static double latitude;
    private static double longitude;
    private static String locality ="";
    private static String country="";

    public static double getLatitude() {
        return latitude;
    }

    public static void setLatitude(double latitude) {
        LocationDataKeeper.latitude = latitude;
    }

    public static double getLongitude() {
        return longitude;
    }

    public static void setLongitude(double longitude) {
        LocationDataKeeper.longitude = longitude;
    }

    public static String getLocality() {
        return locality;
    }

    public static void setLocality(String locality) {
        LocationDataKeeper.locality = locality;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        LocationDataKeeper.country = country;

    }
}
