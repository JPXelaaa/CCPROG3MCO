import java.util.*;

public class Room {
    private String name;
    private double basePrice;
    private List<Reservation> reservations;
    private RoomType roomType;
    private Hotel hotel;
    private double[] priceModifiers;

    public Room(String name, double basePrice, RoomType roomType, double[] priceModifiers) {
        this.name = name;
        this.basePrice = basePrice;
        this.roomType = roomType;
        this.priceModifiers = priceModifiers;
        this.reservations = new ArrayList<>();
    }

    public double calculatePrice(double basePrice, RoomType roomType) {
        switch (roomType) {
            case DELUXE:
                return basePrice * 1.20;
            case EXECUTIVE:
                return basePrice * 1.35;
            case STANDARD:
            default:
                return basePrice;
        }
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public boolean isAvailable(int checkInDate, int checkOutDate) {
        for (Reservation reservation : reservations) {
            if (!(checkOutDate <= reservation.getCheckInDate() || checkInDate >= reservation.getCheckOutDate())) {
                return false;
            }
        }
        return true;
    }

    public boolean[] getAvailability() {
        boolean[] availability = new boolean[31];
        Arrays.fill(availability, true);

        for (Reservation reservation : reservations) {
            for (int day = reservation.getCheckInDate(); day < reservation.getCheckOutDate(); day++) {
                availability[day - 1] = false;
            }
        }

        return availability;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public double getDailyPrice(int day) {
        double modifier;
        if (day >= 1 && day <= 30) {
            modifier = priceModifiers[day - 1];
        } else {
            System.out.println("Invalid day. Please enter a day between 1 and 30.");
            return 1.0; // Default modifier
        }
        return calculatePrice(basePrice, roomType) * modifier;
    }

}
