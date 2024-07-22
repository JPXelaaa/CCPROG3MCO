/* Hotel class
    This class represents a hotel, managing its rooms and reservations. It provides methods for adding/removing rooms,
    finding rooms and reservations, and calculating earnings.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private static final int MAX_ROOMS = 50;
    private double basePrice = 1299.0; // Default base price
    private static final Scanner scanner = new Scanner(System.in);
    
    /* Constructor
        a. Purpose: Initializes the hotel with a name and adds a default room.
        b. Parameters: 
            - name (String): The name of the hotel.
        c. Return type: None
    */
    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        addDefaultRoom();
    }
    /* addDefaultRoom Method
        a. Purpose: Adds a default room to the hotel.
        b. Parameters: None
        c. Return type: None
    */
    private void addDefaultRoom() {
        Room defaultRoom = new Room("A01", basePrice);
        rooms.add(defaultRoom);
    }
    /* generateRoomName Method
        a. Purpose: Generates a unique room name for new rooms.
        b. Parameters: None
        c. Return type: String - the generated room name.
    */
    private String generateRoomName() {
        int roomNumber = rooms.size() + 1; // Start from 2 since "A01" is already present
        return String.format("A%02d", roomNumber);
    }
    // Getters and Setters
    /* getName Method
        a. Purpose: Gets the name of the hotel.
        b. Parameters: None
        c. Return type: String - the name of the hotel.
    */
    public String getName() {
        return name;
    }
    /* setName Method
        a. Purpose: Sets the name of the hotel.
        b. Parameters: 
            - name (String): The new name of the hotel.
        c. Return type: None
    */
    public void setName(String name) {
        this.name = name;
    }
    /* getRooms Method
        a. Purpose: Gets the list of rooms in the hotel.
        b. Parameters: None
        c. Return type: List<Room> - the list of rooms.
    */
    public List<Room> getRooms() {
        return rooms;
    }
    /* getReservations Method
        a. Purpose: Gets the list of reservations for the hotel.
        b. Parameters: None
        c. Return type: List<Reservation> - the list of reservations.
    */
    public List<Reservation> getReservations() {
        return reservations;
    }
    /* getBasePrice Method
        a. Purpose: Gets the base price for rooms in the hotel.
        b. Parameters: None
        c. Return type: double - the base price.
    */
    public double getBasePrice() {
        return basePrice;
    }
    /* setBasePrice Method
        a. Purpose: Sets the base price for rooms and updates all rooms with the new base price.
        b. Parameters: 
            - basePrice (double): The new base price for rooms.
        c. Return type: None
    */
    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        for (Room room : rooms) {
            room.setBasePrice(basePrice);
        }
    }
    /* addRoom Method
        a. Purpose: Adds a new room to the hotel.
        b. Parameters: None
        c. Return type: boolean - true if the room is successfully added, false otherwise.
    */
    public boolean addRoom() {
        String roomName = generateRoomName();
        if (rooms.size() >= MAX_ROOMS) {
            System.out.println("Maximum room limit reached.");
            return false;
        }
        if (findRoom(roomName) != null) {
            System.out.println("Room name must be unique.");
            return false;
        }
        rooms.add(new Room(roomName, basePrice));
            System.out.println("Room " + roomName + " added successfully.");
        return true;
    }

    /* removeRoom Method
        a. Purpose: Removes a room from the hotel if it has no active reservations.
        b. Parameters: 
            - roomName (String): The name of the room to remove.
        c. Return type: boolean - true if the room is successfully removed, false otherwise.
    */
    public boolean removeRoom(String roomName) {
        String response;
        Room roomToRemove = findRoom(roomName);
        if (roomToRemove != null && roomToRemove.getReservations().isEmpty()) {
            System.out.print("\nDo you want to remove this room? (Y/N): ");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("N")) {
              return false;
            }
            else if(!response.equalsIgnoreCase("Y")) {
              System.out.print("\nInvalid choice. Exiting modification.\n");
              return false;
            }
            rooms.remove(roomToRemove);
            return true;
        }
        else if(!roomToRemove.getReservations().isEmpty())
        {
        System.out.println("Room cannot be removed. It has active reservations.");
        }
        else{
        System.out.println("Invalid Input");
        }
        return false;
    }

    /* findRoom Method
        a. Purpose: Finds a room by its name.
        b. Parameters: 
            - roomName (String): The name of the room to find.
        c. Return type: Room - the room object if found, otherwise null.
    */
    public Room findRoom(String roomName) {
        for (Room room : rooms) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    /* removeReservation Method
        a. Purpose: Removes a reservation from the hotel.
        b. Parameters: 
            - guestName (String): The name of the guest with the reservation.
            - checkInDate (int): The check-in date of the reservation.
            - checkOutDate (int): The check-out date of the reservation.
        c. Return type: boolean - true if the reservation is successfully removed, false otherwise.
    */
    public boolean removeReservation(String guestName, int checkInDate, int checkOutDate) {
        String response;
        Reservation reservationToRemove = findReservation(guestName, checkInDate, checkOutDate);
        
        if (reservationToRemove != null) {
            System.out.print("\nDo you want to remove this reservation? (Y/N): ");
            response = scanner.nextLine();
            if (response.equalsIgnoreCase("N")) {
              return false;
            }
            else if(!response.equalsIgnoreCase("Y")) {
              System.out.print("\nInvalid choice. Exiting modification.");
              return false;
            }
            reservations.remove(reservationToRemove);
            reservationToRemove.getRoom().getReservations().remove(reservationToRemove);
            return true;
        }
        System.out.println("Reservation not found.");
        return false;
    }

    /* findReservation Method
        a. Purpose: Finds a reservation by guest name and date range.
        b. Parameters: 
            - guestName (String): The name of the guest with the reservation.
            - checkInDate (int): The check-in date of the reservation.
            - checkOutDate (int): The check-out date of the reservation.
        c. Return type: Reservation - the reservation object if found, otherwise null.
    */
    public Reservation findReservation(String guestName, int checkInDate, int checkOutDate) {
        for (Reservation reservation : reservations) {
            if (reservation.getGuestName().equals(guestName) &&
                    reservation.getCheckInDate() == checkInDate &&
                    reservation.getCheckOutDate() == checkOutDate) {
                return reservation;
            }
        }
        return null;
    }

    /* removeHotel Method
        a. Purpose: Determines if the hotel can be removed by checking for active reservations.
        b. Parameters: None
        c. Return type: boolean - true if the hotel can be removed, false otherwise.
    */
    public boolean removeHotel() {
        if (reservations.isEmpty()) {
            return true;
        }
        return false;
    }

    /* calculateEarnings Method
        a. Purpose: Calculates the total earnings from all reservations in the hotel.
        b. Parameters: None
        c. Return type: double - the total earnings.
    */
    public double calculateEarnings() {
        double total = 0;
        for (Reservation reservation : reservations) {
            total += reservation.calculateTotalPrice();
        }
        return total;
    }

    /* getAvailableRooms Method
        a. Purpose: Gets a list of rooms available for a specific date range.
        b. Parameters: 
            - checkInDate (int): The check-in date for availability.
            - checkOutDate (int): The check-out date for availability.
        c. Return type: List<Room> - the list of available rooms.
    */
    public List<Room> getAvailableRooms(int checkInDate, int checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.isAvailable(checkInDate, checkOutDate)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    /* getRoomDetails Method
        a. Purpose: Retrieves details of a specific room in the hotel.
        b. Parameters: 
            - roomName (String): The name of the room whose details are to be retrieved.
        c. Return type: String - details of the room if found, otherwise an error message.
    */
    public String getRoomDetails(String roomName) {
        Room room = findRoom(roomName);
        boolean[] availability;
        StringBuilder details;
    
        if (room == null) {
            return "Room not found.";
        }
    
        availability = room.getAvailability();
        details = new StringBuilder();
        details.append("Room Name: ").append(room.getName()).append("\n");
        details.append("Price per Night: ").append(room.getBasePrice()).append("\n");
        details.append("Availability:\n");
    
        for (int day = 1; day <= availability.length; day++) {
            details.append(String.format("Day %2d: %s\n", day, availability[day - 1] ? "Available" : "Booked"));
        }
    
        return details.toString();
    }
}