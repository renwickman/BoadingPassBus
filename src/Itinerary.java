import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Itinerary {
    private Application passenger;
    Path filePath;
    public Itinerary(Application newApplicant){
        this.passenger = newApplicant;
        createBoardPass();
        generatePass();
        filePath = Paths.get(System.getProperty("user.dir") + "/src/itinerary/" + passenger.getBoarding_pass() + ".txt");
        createFile();
        writeToAFile();
        System.out.println("Boarding pass has been created! Please check your itinerary for details.");
    }

    String createBoardPass(){
        Random rand = new Random();
        String pass ="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder res = new StringBuilder();
        for (int i = 0; i <= 12; i++){
            int randIndex = rand.nextInt(pass.length());
                res.append(pass.charAt(randIndex));
            }
            return res.toString();
        }

    void generatePass(){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            passenger.setBoarding_pass(createBoardPass());
            session.update(passenger);
            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
    }
    public void createFile() {
        try {
            Files.createFile(filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeToAFile() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        try{
            Files.writeString(filePath, "Date purchased: "+formatter.format(date) + passenger.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}