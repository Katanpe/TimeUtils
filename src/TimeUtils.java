

public class TimeUtils {

    public String secToTime(int a) {
        int hh, mm, ss;

        if (a < 0 || a >= 32000) {
        	throw new IllegalArgumentException("Invalid input");
        }

        hh = a / 3600;
        a = a - (3600 * hh);
        mm = a / 60;
        ss = a - (60 * mm);

        String res = hh + ":";
        if (mm < 10) {
            res = res + "0" + mm + ":";
        } else {
            res = res + mm + ":";
        }
        if (ss < 10) {
            res = res + "0" + ss;
        } else {
            res = res + ss;
        }

        return res;
    }
}
