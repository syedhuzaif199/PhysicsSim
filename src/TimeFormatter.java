public class TimeFormatter {
    public static String colonSeparated(long millis) {
        int mil = (int)(millis % 1000);
        int sec = (int)(millis / 1000);
        int min = sec / 60;
        sec = sec % 60;
        int hr = min / 60;
        min = min % 60;

        return String.format("%02d",hr) + " : " + String.format("%02d",min) + " : " + String.format("%02d",sec) + " : " + String.format("%04d",mil);
    }

    public static String named(long millis) {
        int mil = (int) (millis % 1000);
        int sec = (int) (millis / 1000);
        int min = sec / 60;
        sec = sec % 60;
        int hr = min / 60;
        min = min % 60;

        return hr + "hr " + min + "min " + sec + "s " + String.format("%04d",mil) + "ms";
    }
}
