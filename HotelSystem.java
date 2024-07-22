/* HotelSystem Class
    This class manages a collection of hotels, providing functionality for adding, removing, 
    listing, and viewing hotels, as well as managing bookings and reservations.
*/

import java.util.ArrayList;
import java.util.List;

public class HotelSystem {
    private List<Hotel> hotels;

    /* Constructor
        a. Purpose: Initializes the hotel system with an empty list of hotels.
        b. Parameters: None
        c. Return type: None
    */
    public HotelSystem() {
        this.hotels = new ArrayList<>();
    }

    /* createHotel Method
        a. Purpose: Creates and adds a new hotel to the system.
        b. Parameters: 
            - hotelName (String): The name of the hotel to be added.
        c. Return type: boolean - true if the hotel is successfully added, false if a hotel with the same name already exists.
    */
    public boolean createHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return false; // Returns if there is a similar instance of the hotel name
            }
        }
        hotels.add(new Hotel(hotelName));
        return true;
    }

    /* findHotel Method
        a. Purpose: Finds a hotel by its name.
        b. Parameters: 
            - hotelName (String): The name of the hotel to find.
        c. Return type: Hotel - the hotel object if found, otherwise null.
    */
    public Hotel findHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return hotel;
            }
        }
        return null;
    }

    /* removeHotel Method
        a. Purpose: Removes a hotel from the system.
        b. Parameters: 
            - hotelName (String): The name of the hotel to remove.
        c. Return type: boolean - true if the hotel is successfully removed, false otherwise.
    */
    public boolean removeHotel(String hotelName) {
        Hotel hotelToRemove = findHotel(hotelName);
        if (hotelToRemove != null && hotelToRemove.removeHotel()) {
            hotels.remove(hotelToRemove);
            return true;
        }
        System.out.println("Hotel cannot be removed. It has active reservations or does not exist.");
        return false;
    }

    /* listHotels Method
        a. Purpose: Lists all hotels in the system.
        b. Parameters: None
        c. Return type: void
    */
    public void listHotels() {
        if (hotels.isEmpty()) {
            System.out.println("No hotels available.");
        } else {
            System.out.println("Hotels:");
            for (Hotel hotel : hotels) {
                System.out.println(" - " + hotel.getName());
            }
        }
    }

    /* viewHotel Method
        a. Purpose: Displays detailed information about a specific hotel.
        b. Parameters: 
            - hotelName (String): The name of the hotel to view.
        c. Return type: void
    */
    public void viewHotel(String hotelName) {
        Hotel hotel = findHotel(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }
        System.out.println("\nHotel Name: " + hotel.getName());
        System.out.println("Total Rooms: " + hotel.getRooms().size());
        System.out.println("Estimated Earnings: " + hotel.calculateEarnings());
        System.out.println("\nRooms: ");
        for (Room room : hotel.getRooms()) {
            System.out.println(" - " + room.getName() + " | Base Price: " + room.getBasePrice());
        }
    }
    
    /* viewHotelRooms Method
        a. Purpose: Displays all rooms of a specific hotel.
        b. Parameters: 
            - hotelName (String): The name of the hotel whose rooms are to be viewed.
        c. Return type: void
    */
    public void viewHotelRooms(String hotelName) {
        Hotel hotel = findHotel(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }
        System.out.println("Rooms: ");
        for (Room room : hotel.getRooms()) {
            System.out.println(" - " + room.getName() + " | Base Price: " + room.getBasePrice());
        }
    }

    /* simulateBooking Method
        a. Purpose: Simulates the booking of a room in a hotel.
        b. Parameters: 
            - hotelName (String): The name of the hotel where the booking is to be made.
            - guestName (String): The name of the guest making the booking.
            - checkInDate (int): The check-in date for the booking.
            - checkOutDate (int): The check-out date for the booking.
            - roomName (String): The name of the room to be booked.
        c. Return type: boolean - true if the booking is successful, false otherwise.
    */
    public boolean simulateBooking(String hotelName, String guestName, int checkInDate, int checkOutDate, String roomName) {
        Hotel hotel = findHotel(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return false;
        }
        Room room = hotel.findRoom(roomName);
        if (room == null) {
            System.out.println("Room not found.");
            return false;
        }
        if (!room.isAvailable(checkInDate, checkOutDate)) {
            System.out.println("Room is not available for the selected dates.");
            return false;
        }
        Reservation reservation = new Reservation(guestName, checkInDate, checkOutDate, room);
        hotel.getReservations().add(reservation);
        return true;
    }

    /* getRoomDetails Method
        a. Purpose: Retrieves details of a specific room in a hotel.
        b. Parameters: 
            - hotelName (String): The name of the hotel containing the room.
            - roomName (String): The name of the room whose details are to be retrieved.
        c. Return type: String - details of the room if found, otherwise an error message.
    */
    public String getRoomDetails(String hotelName, String roomName) {
        Hotel hotel = findHotel(hotelName);
        if (hotel == null) {
            return "Hotel not found.";
        }
        return hotel.getRoomDetails(roomName);
    }
}