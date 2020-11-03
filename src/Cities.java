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

    /*  public static void main(String[] args) {
        cityList.add(new Locations(1, "New York", 40.7648, -73.9808));
        cityList.add(new Locations(2, "Los Angeles", 34.05223, -118.24368));
        cityList.add(new Locations(3, "Detroit", 42.331427, -83.045754));
        cityList.add(new Locations(4, "Phoenix", 33.448377, -112.074037));
        cityList.add(new Locations(5, "Louisville", 38.252665, -85.758456));
        cityList.add(new Locations(6, "Indianapolis", 39.76863, -86.15804));
    }*/
}
