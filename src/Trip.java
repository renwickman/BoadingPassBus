import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Trip {
    private Application passenger;
    double distance;
    double minutes;
    double hours;
    Cities cities;
    Locations depart;
    Locations arrive;
    ArrayList<String> times;
    String departTime;
    String departDate;
    String arriveTime;

    public Trip(Application newApplicant) {
        this.passenger = newApplicant;
        cities = new Cities();
        times = new ArrayList<>();
        times.add("06:00 AM");
        times.add("08:00 AM");
        times.add("10:00 AM");
        times.add("12:00 PM");
        times.add("02:00 PM");
        times.add("04:00 PM");
        times.add("06:00 PM");
        times.add("08:00 PM");
        times.add("10:00 PM");
        updateTrip();
    }

    public void updateTrip() {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            passenger.setOrigin(enterDepart());
            passenger.setDestination(enterArrive());
            passenger.setDepartDate(departDate());
            passenger.setDepartTime(departTime());
            passenger.setEta(estTimeArrive());
            session.update(passenger);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }

    public String enterDepart() {
        System.out.println("Hi " + passenger.getName() + "! Glad that you chose Drive Time.  Now let's get started.");
        depart = getLocation("Where are you departing from?");
        return depart.getTimeZoneString();
    }

    public String enterArrive() {
        while(true) {
            arrive = getLocation("Where are you arriving to?");
            //could also make changes in cities class to remove depart location from list.
            if (!arrive.equals(depart))
                return arrive.getTimeZoneString();
            System.out.println("You cannot arrive where you've departed!");
        }
    }

    public Locations getLocation(String message) {
        Scanner scan = new Scanner(System.in);
        System.out.println(message);
        System.out.println(cities.toString());
        while (true) {
            scan.reset();
            try {
                return cities.getCityList().get(Integer.parseInt(scan.nextLine()) - 1);
            } catch (Exception e) {
                System.out.println("please enter a valid choice!");
            }
        }
    }

    public String departDate() {
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        df.setLenient(false);
        while (true) {
            Scanner scanDepartDate = new Scanner(System.in);
            System.out.println("When do you want to leave?");
            System.out.println("Format: MM/DD/YYYY");
            departDate = scanDepartDate.nextLine();
            String[] date = new String[0];
            try {
                if (departDate.matches("[0-9]{2}+" + "/[0-9]{2}+" + "/[0-9]{4}") && df.parse(departDate).compareTo(df.parse(df.format(new Date())))>=0)
                        return departDate;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public String departTime() {
        int count = 1;
        while (true) {
            Scanner scanDepartTime = new Scanner(System.in);
            System.out.println("What time do you want to leave? (Select a number to choose a time)");
            for (String leaveTime : times) {
                System.out.println(count + ". " + leaveTime);
                count++;
            }
            try {
                departTime = times.get(Integer.parseInt(scanDepartTime.nextLine()) - 1);
                return departTime;
            } catch (Exception e) {
                System.out.println("Please make a valid entry!");
            }
        }
    }

    public String estTimeArrive() {
        final String DATE_FORMAT = "MM/dd/yyyy hh:mm a";

        String leaveDateTime = departDate + " " + departTime;
        LocalDateTime ldt = LocalDateTime.parse(leaveDateTime, DateTimeFormatter.ofPattern(DATE_FORMAT));

        double earthRadius = 6371.01 * 0.621;

        distance = Math.round(earthRadius * Math.acos(Math.sin(depart.getLat()) * Math.sin(arrive.getLat())
                + Math.cos(depart.getLat()) * Math.cos(arrive.getLat()) * Math.cos(depart.getLon() - arrive.getLon())));

        passenger.setDistance(distance);

        double distance1 = distance / 50;
        hours = Math.floor(distance1);
        minutes = Math.ceil((distance1 - hours) * 60);

        LocalDateTime ldt2 = ldt.plusHours((long) hours).plusMinutes((long) minutes);


        ZoneId fromId = ZoneId.of(depart.getTimeZoneString());
        ZoneId toId = ZoneId.of(arrive.getTimeZoneString());

        ZonedDateTime currentTime = ldt2.atZone(fromId);

        ZonedDateTime newTime = currentTime.withZoneSameInstant(toId);

        //condition to add day
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern(DATE_FORMAT);
        arriveTime = dateFormat2.format(newTime);
        System.out.println(arriveTime);
        return arriveTime;

    }


}