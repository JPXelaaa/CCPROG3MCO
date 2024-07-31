
import java.util.*;

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
                System.out.println("Hotel name must be unique. Failed to create hotel.");
                return false; // Returns if there is a similar instance of the hotel name
            }
        }
        System.out.println("Hotel ["+ hotelName +"] created successfully.");
        System.out.println("A default STANDARD room named 'S01' has been added to the hotel.");
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
    /* listHotels Method
        a. Purpose: Creates a list of all hotels in the system.
        b. Parameters: None
        c. Return type: List<String>
    */
    public List<String> guiListHotels() {
        List<String> hotelNames = new ArrayList<>();
        for (Hotel hotel : hotels) {
            hotelNames.add(hotel.getName());
        }
        return hotelNames;
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
        System.out.println("\nSTANDARD ROOMS:");
        for (Room room : hotel.getRooms()) {
            if(room.getRoomType() == RoomType.STANDARD)
                System.out.println(" - " + room.getName() + " | Base Price: " + room.calculatePrice(room.getBasePrice(), room.getRoomType()));
        }
        System.out.println("\nDELUXE ROOMS:");
        for (Room room : hotel.getRooms()) {
            if(room.getRoomType() == RoomType.DELUXE)
                System.out.println(" - " + room.getName() + " | Base Price: " + room.calculatePrice(room.getBasePrice(), room.getRoomType()));
        }
        System.out.println("\nEXECUTIVE ROOMS:");
        for (Room room : hotel.getRooms()) {
            if(room.getRoomType() == RoomType.EXECUTIVE)
                System.out.println(" - " + room.getName() + " | Base Price: " + room.calculatePrice(room.getBasePrice(), room.getRoomType()));
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
        System.out.println("\nSTANDARD ROOMS:");
        for (Room room : hotel.getRooms()) {
            if(room.getRoomType() == RoomType.STANDARD)
                System.out.println(" - " + room.getName() + " | Base Price: " + room.calculatePrice(room.getBasePrice(), room.getRoomType()));
        }
        System.out.println("\nDELUXE ROOMS:");
        for (Room room : hotel.getRooms()) {
            if(room.getRoomType() == RoomType.DELUXE)
                System.out.println(" - " + room.getName() + " | Base Price: " + room.calculatePrice(room.getBasePrice(), room.getRoomType()));
        }
        System.out.println("\nEXECUTIVE ROOMS:");
        for (Room room : hotel.getRooms()) {
            if(room.getRoomType() == RoomType.EXECUTIVE)
                System.out.println(" - " + room.getName() + " | Base Price: " + room.calculatePrice(room.getBasePrice(), room.getRoomType()));
        }

    }
}