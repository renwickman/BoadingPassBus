public class Main {

    public static void main(String[] args) {

        Passenger pass = new Passenger();
        new Trip(pass.getNewApplicant());
        new Price(pass.getNewApplicant());
        new Itinerary(pass.getNewApplicant());

    }
}