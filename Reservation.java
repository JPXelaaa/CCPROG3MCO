/* Reservation class
    This class represents a reservation for a hotel room. It manages guest information, check-in and check-out dates,
    room details, and provides methods for price calculation and overlap checking.
*/

public class Reservation {
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room room;
    private double totalPrice;
    
    /* Constructor
        a. Purpose: Initializes a reservation with guest details, booking dates, and associated room.
        b. Parameters: 
            - guestName (String): The name of the guest making the reservation.
            - checkInDate (int): The check-in date for the reservation.
            - checkOutDate (int): The check-out date for the reservation.
            - room (Room): The room being reserved.
        c. Return type: None
    */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.totalPrice = calculateTotalPrice();
        room.getReservations().add(this);
    }

    // Getters

    /* getGuestName Method
        a. Purpose: Gets the guest name associated with the reservation.
        b. Parameters: None
        c. Return type: String - the guest name.
    */
    public String getGuestName() {
        return guestName;
    }
    
    /* getCheckInDate Method
        a. Purpose: Gets the check-in date for the reservation.
        b. Parameters: None
        c. Return type: int - the check-in date.
    */
    public int getCheckInDate() {
        return checkInDate;
    }

    /* getCheckOutDate Method
        a. Purpose: Gets the check-out date for the reservation.
        b. Parameters: None
        c. Return type: int - the check-out date.
    */
    public int getCheckOutDate() {
        return checkOutDate;
    }

    /* getRoom Method
        a. Purpose: Gets the room associated with the reservation.
        b. Parameters: None
        c. Return type: Room - the room object.
    */
    public Room getRoom() {
        return room;
    }

    /* calculateTotalPrice Method
        a. Purpose: Calculates the total price for the reservation based on the room's base price and duration of stay.
        b. Parameters: None
        c. Return type: double - the total price of the reservation.
    */
    public double calculateTotalPrice() {
        return room.getBasePrice() * (checkOutDate - checkInDate);
    }

    /* getPriceBreakdown Method
        a. Purpose: Provides a breakdown of the reservation price including base price, total nights, and total price.
        b. Parameters: None
        c. Return type: String - a formatted string with the price breakdown.
    */
    public String getPriceBreakdown() {
        return "Base Price: " + room.getBasePrice() + ", Total Nights: " + (checkOutDate - checkInDate) +
                ", Total Price: " + totalPrice;
    }

    /* overlaps Method
        a. Purpose: Checks if this reservation overlaps with another reservation based on their booking dates.
        b. Parameters: 
            - checkIn (int): The check-in date of the other reservation.
            - checkOut (int): The check-out date of the other reservation.
        c. Return type: boolean - true if there is an overlap, false otherwise.
    */
    public boolean overlaps(int checkIn, int checkOut) {
        return !(checkOut <= this.checkInDate || checkIn >= this.checkOutDate);
    }
}
