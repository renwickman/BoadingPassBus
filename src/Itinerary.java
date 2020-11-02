import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class Itinerary {

    Path filePath = Paths.get(System.getProperty("user.dir") + "\\src\\itinerary.txt");

    public void createFile() {
        try {
            Files.createFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String createBoardPass(){
        Random rand = new Random();
        String pass ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= 18; i++){
            int randIndex = rand.nextInt(pass.length());
                res.append(pass.charAt(randIndex));
            }
            return res.toString();
        }

    void generatePass(int appId){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            Application currentApp = session.get(Application.class, appId);
            currentApp.setBoarding_pass(createBoardPass());
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }


    public void writeToAFile() {
        try{
//            Files.writeString(filePath, Get Today's Date);
//            Files.writeString(filePath, Passenger.getName);
//            Files.writeString(filePath, Passenger.getAge);
//            Files.writeString(filePath, Passenger.getGender);
//            Files.writeString(filePath, Passenger.getPhone);
//            Files.writeString(filePath, Passenger.getEmail);
//            Files.writeString(filePath, Passenger.getOrigin);
//            Files.writeString(filePath, Passenger.getDestination);
//            Files.writeString(filePath, Passenger.getDepartDate);
//            Files.writeString(filePath, Passenger.getDepartTime);
//            Files.writeString(filePath, Passenger.getTotalPrice);
//            Files.writeString(filePath, Passenger.getEta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}