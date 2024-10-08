
public class Reservation {
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room room;
    private double totalPrice;
    private String discountCode;
    /* Constructor
        a. Purpose: Initializes a reservation with guest details, booking dates, and associated room.
        b. Parameters:
            - guestName (String): The name of the guest making the reservation.
            - checkInDate (int): The check-in date for the reservation.
            - checkOutDate (int): The check-out date for the reservation.
            - room (Room): The room being reserved.
        c. Return type: None
    */
    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, String discountCode) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.discountCode = discountCode;
        this.totalPrice = calculateTotalPrice();
        room.getReservations().add(this);
    }
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
        double totalPrice = 0.0;
        for (int day = checkInDate; day < checkOutDate; day++) {
            totalPrice += room.getDailyPrice(day);
        }
        if (discountCode.equals("I_WORK_HERE")) {
            totalPrice *= 0.9; // 10% discount
        } else if (discountCode.equals("STAY4_GET1") && (checkOutDate - checkInDate) >= 5) {
            totalPrice -= room.calculatePrice(room.getBasePrice(), room.getRoomType()); // 1 day free
        } else if (discountCode.equals("PAYDAY") && (coversDay(15) || coversDay(30))) {
            totalPrice *= 0.93; // 7% discount
        } else if (discountCode.equals("NONE")){
        }

        return totalPrice;
    }
    /* coversDay Method
        a. Purpose: Checker for if the day covers the checkInDate &checkOutDate
        b. Parameters: day (int): day checked
        c. Return type: boolean
    */
    private boolean coversDay(int day) {
        return checkInDate <= day && day < checkOutDate;
    }
}
