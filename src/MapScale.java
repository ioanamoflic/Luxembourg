import java.lang.Math;
public class MapScale {

    public static final double ScreenWidth = 500;
    public static final double ScreenHeight = 700;
    public static double latx = 5.734153;
    public static double laty = 6.531256;
    public static double lonx = 50.182918;
    public static double lony = 49.441140;
    public static double latyt = laty - latx;
    public static double lonyt = lony - lonx;

    public static Integer lat2y(double lat) {
        return 50 + (int)(ScreenWidth / (latyt/(lat-latx)));
    }
    public static Integer lon2x(double lon) {
        return  (int)(ScreenHeight / (lonyt/(lon-lonx)));
    }
}