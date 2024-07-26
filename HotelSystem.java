
import java.util.*;

public class HotelSystem {
    private List<Hotel> hotels;


    public HotelSystem() {
        this.hotels = new ArrayList<>();
    }

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

    public Hotel findHotel(String hotelName) {
        for (Hotel hotel : hotels) {
            if (hotel.getName().equals(hotelName)) {
                return hotel;
            }
        }
        return null;
    }
    public boolean removeHotel(String hotelName) {
        Hotel hotelToRemove = findHotel(hotelName);
        if (hotelToRemove != null && hotelToRemove.removeHotel()) {
            hotels.remove(hotelToRemove);
            return true;
        }
        System.out.println("Hotel cannot be removed. It has active reservations or does not exist.");
        return false;
    }

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