import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.model.LatLng;
import com.breadTravel.entity.WorksPreview;
import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class T {
    public static void main(String[] args) {
//        String str="中国,衡阳市 lat/lng: (27.295373,112.801209)";
//        System.out.println(str.substring(0,str.indexOf(" ")));
//        String jwd=str.substring(str.indexOf("("),str.indexOf(")")+1);
//        System.out.println(jwd);
//        System.out.println(jwd.substring(1,jwd.indexOf(",")));
//        System.out.println(jwd.substring(jwd.indexOf(",")+1,jwd.lastIndexOf(")")));
        a("(27.2476229000,112.7898094600)","(27.295373,112.801209)");
//        System.out.println();
    }

    public static void a(String point1,String point2){
        float dis= AMapUtils.calculateLineDistance(point(point1),point(point2))/1000;
        System.out.println(dis);
    }

    public static LatLng point(String str){
        String jwd=str.substring(str.indexOf("("),str.indexOf(")")+1);
        String wd=jwd.substring(1,jwd.indexOf(","));
        String jd=jwd.substring(jwd.indexOf(",")+1,jwd.lastIndexOf(")"));
        LatLng latLng=new LatLng(Double.valueOf(wd),Double.valueOf(jd));
        return latLng;
    }

    //计算天数
    public static int daysBetween(String smdate, String bdate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(smdate));
            long time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            long time2 = cal.getTimeInMillis();
            long between_days = (time2 - time1) / (1000*3600*24);
            return Integer.parseInt(String.valueOf(between_days));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
