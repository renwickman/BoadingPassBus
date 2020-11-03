public class Main {

    public static void main(String[] args) {

        Passenger pass = new Passenger();
/*        Trip trippy = new Trip(pass.getNewApplicant());
        trippy.enterDepart();
        trippy.enterArrive();
        trippy.departDate();
        trippy.departTime();
        trippy.estTimeArrive();
        trippy.updateTrip(pass.getNewApplicant().getId());
        Price pricey = new Price(pass.getNewApplicant());
        pricey.priceUpdate(pass.getNewApplicant().getId());
        Itinerary itin = new Itinerary(pass.getNewApplicant());
        itin.createBoardPass();
        itin.generatePass(pass.getNewApplicant().getId());
        itin.createFile();
        itin.writeToAFile();

    }

}
