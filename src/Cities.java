import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.ArrayList;
import java.util.List;

public class Cities {
    public List<Locations> cityList = new ArrayList<>();

    public Cities(){
        cityList.add(new Locations(1,"America", "New_York", 40.7648, -73.9808));
        cityList.add(new Locations(2, "America", "Los_Angeles", 34.05223, -118.24368));
        cityList.add(new Locations(3, "America", "Detroit", 42.331427, -83.045754));
        cityList.add(new Locations(4, "America", "Phoenix", 33.448377, -112.074037));
        cityList.add(new Locations(5, "America", "Louisville", 38.252665, -85.758456));
        cityList.add(new Locations(6, "America", "Indiana", "Indianapolis", 39.76863, -86.15804));
    }
    public List<Locations> getCityList() {

        return cityList;
    }
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Locations location: cityList
             ) {
            sb.append(location.getId() + "." +location.getTimeZoneString() + "" +
                    "\n");
        }
        return sb.toString();
    }
}
