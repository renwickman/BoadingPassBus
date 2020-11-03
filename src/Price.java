import entity.Application;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Price {
    private Application passenger;

    public Price(Application newApplicant){
        this.passenger = newApplicant;
        priceUpdate();
    }

    private float priceCheck(){
        float price = (float) 150.00;

        if (passenger.getGender().equals("F")){
            price = price * .75f;
        }
        else if (passenger.getAge() <= 12){
            price = price * .50f;
        }
        else if (passenger.getAge() >= 60){
            price = price * .40f;
        }
        return price;
    }

    void priceUpdate(){
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Application.class)
                .buildSessionFactory();
        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();
            passenger.setTotal_price(priceCheck());
            session.update(passenger);
            session.getTransaction().commit();
        } finally {
            factory.close();
        }
    }

}
