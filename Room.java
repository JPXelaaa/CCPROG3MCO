/* Room.java
    This class represents a hotel room. It manages the room's name, base price, and reservations, 
    and provides methods to check availability and manage room details.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Room {
    private String name;
    private double basePrice;
    private List<Reservation> reservations;
    
    /* Constructor
        a. Purpose: Initializes the room with a name and base price.
        b. Parameters: 
            - name (String): The name of the room.
            - basePrice (double): The base price of the room.
        c. Return type: None
    */
    public Room(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
        this.reservations = new ArrayList<>();
    }

    // Getters and Setters
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
}