import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.type.descriptor.java.TimeZoneTypeDescriptor;

import javax.xml.stream.Location;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class Trip {
    private Application passenger;
    ZonedDateTime endDateTime;
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
    }

  public String enterDepart() {

        Scanner scanDepart = new Scanner(System.in);
        System.out.println("Hi " + passenger.getName() + "! Glad that you chose Drive Time.  Now let's get started.");
        System.out.println("Where are you departing from?");
      for (Locations location: cities.getCityList()
           ) {
          System.out.println(location.getId() + "." +location.getTimeZoneString());
      }

      try{
          depart = cities.getCityList().get(Integer.parseInt(scanDepart.nextLine())-1);
      } catch(Exception e){
          System.out.println(e.getMessage());
      }
      return depart.getTimeZoneString();
    }

    public String enterArrive() {
        Scanner scanArrive = new Scanner(System.in);
        System.out.println("Where are you arriving to?");
        for (Locations location: cities.getCityList()
        ) {
            System.out.println(location.getId() + "." +location.getTimeZoneString());
        }
        try{
            arrive = cities.getCityList().get(Integer.parseInt(scanArrive.nextLine())-1);
        } catch(Exception e){

        }
        return arrive.getTimeZoneString();
    }

    public String departDate(){
        while (true) {
            Scanner scanDepartDate = new Scanner(System.in);
            System.out.println("When do you want to leave?");
            System.out.println("Format: MM/DD/YYYY ");
            departDate = scanDepartDate.nextLine();
            try {
                if (departDate.matches("[0-9]{2}+" + "/[0-9]{2}+" + "/[0-9]{4}"))
                    return departDate;
            } catch (Exception e) {
                System.out.println("Please enter a valid phone number");
            }
        }
    }

    public String departTime() {
        int count = 1;
        while (true) {
            Scanner scanDepartTime = new Scanner(System.in);
            System.out.println("What time do you want to leave? (Select a number to choose a time)");
            for (String leaveTime : times) {
                System.out.println(count + " : " + leaveTime);
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

        double distance1 = distance / 50;
        hours = Math.floor(distance1);
        minutes = Math.ceil((distance1 - hours) * 60);
        Calendar endTime = new GregorianCalendar(TimeZone.getTimeZone(arrive.getTimeZoneString()));

        ZoneId zoneId = ZoneId.of(arrive.getTimeZoneString());

        ZonedDateTime dateTime = ldt.atZone(zoneId);

        //condition to add day
        endTime.add(Calendar.HOUR, (int) hours);
        endTime.add(Calendar.MINUTE, (int) minutes);


        endDateTime = dateTime.withZoneSameInstant(zoneId);
        System.out.println(endDateTime);
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);
        arriveTime = dateFormat.format(endDateTime);
        System.out.println(arriveTime);
        return arriveTime;

    }

    public void updateTrip(int appId) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Application currentApp = session.get(Application.class, appId);
            currentApp.setOrigin(enterDepart());
            currentApp.setDestination(enterArrive());
            currentApp.setDepartDate(departDate());
            currentApp.setDepartTime(departTime());
            currentApp.setEta(estTimeArrive());
            session.save(currentApp);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
}