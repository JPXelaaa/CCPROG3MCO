import java.util.*;

public class Room {
    private String name;
    private double basePrice;
    private List<Reservation> reservations;
    private RoomType roomType;
    private double[] priceModifiers;

    /* Constructor
            a. Purpose: Initializes the room with a name and base price.
            b. Parameters:
                - name (String): The name of the room.
                - basePrice (double): The base price of the room.
                - roomType (RoomType): The type of the room.
                - priceModifiers (double[]): The price modifiers for each day.
            c. Return type: None
        */
    public Room(String name, double basePrice, RoomType roomType, double[] priceModifiers) {
        this.name = name;
        this.basePrice = basePrice;
        this.roomType = roomType;
        this.priceModifiers = priceModifiers;
        this.reservations = new ArrayList<>();
    }
    /* calculatePrice Method
                a. Purpose: Calculates the price of the room along with the base price and roomtype
                b. Parameters: basePrice (double), roomType (RoomType)
                c. Return type: double - new price
    */
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
    /* getName Method
            a. Purpose: Gets the name of the room.
            b. Parameters: None
            c. Return type: String - the name of the room.
        */
    public String getName() {
        return name;
    }
    /* getBasePrice Method
            a. Purpose: Gets the base price of the room.
            b. Parameters: None
            c. Return type: double - the base price of the room.
        */
    public double getBasePrice() {
        return basePrice;
    }
    /* setBasePrice Method
            a. Purpose: Sets the base price of the room.
            b. Parameters:
                - basePrice (double): The new base price of the room.
            c. Return type: None
        */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }
    /* getReservations Method
         a. Purpose: Gets the list of reservations for the room.
         b. Parameters: None
         c. Return type: List<Reservation> - the list of reservations.
     */
    public List<Reservation> getReservations() {
        return reservations;
    }
    /* isAvailable Method
          a. Purpose: Checks if the room is available for a given date range.
          b. Parameters:
              - checkInDate (int): The check-in date.
              - checkOutDate (int): The check-out date.
          c. Return type: boolean - true if the room is available, false otherwise.
    */
    public boolean isAvailable(int checkInDate, int checkOutDate) {
        for (Reservation reservation : reservations) {
            if (!(checkOutDate <= reservation.getCheckInDate() || checkInDate >= reservation.getCheckOutDate())) {
                return false;
            }
        }
        return true;
    }
    /* getAvailability Method
        a. Purpose: Gets the availability of the room for the entire month.
        b. Parameters: None
        c. Return type: boolean[] - an array representing the availability of each day in the month.
    */
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
    /* getRoomType Method
        a. Purpose: Gets the Room Type
        b. Parameters: None
        c. Return type: RoomType
    */
    public RoomType getRoomType() {
        return roomType;
    }

    /* setRoomType Method
        a. Purpose: Sets the Room Type
        b. Parameters: roomType (RoomType)
        c. Return type: void
    */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    /* getDailyPrice Method
        a. Purpose: Gets the base price of that day.
        b. Parameters: day (int)
        c. Return type: double
    */
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
