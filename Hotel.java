
import java.util.*;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private static final int MAX_ROOMS = 50;
    private double basePrice = 1299.0; // Default base price
    private static final Scanner scanner = new Scanner(System.in);
    private double[] priceModifiers;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.priceModifiers = new double[30];
        Arrays.fill(this.priceModifiers, 1.0); // Default modifier is 100%
        addDefaultRoom();
    }

    private void addDefaultRoom() {
        Room defaultRoom = new Room("S01", basePrice, RoomType.STANDARD, priceModifiers);
        rooms.add(defaultRoom);
    }

    private String generateRoomName(RoomType roomType) {
        int roomNumberStandard = 1;
        int roomNumberDeluxe = 1;
        int roomNumberExecutive = 1;
        for (Room room : rooms) {
            RoomType type = room.getRoomType();
            if (type == RoomType.STANDARD) {
                roomNumberStandard++;
            }
            if (type == RoomType.DELUXE) {
                roomNumberDeluxe++;
            }
            if (type == RoomType.EXECUTIVE) {
                roomNumberExecutive++;
            }
        }
        if(roomType == RoomType.STANDARD) {
            return String.format("S%02d", roomNumberStandard);
        }
        else if(roomType == RoomType.DELUXE) {
            return String.format("D%02d", roomNumberDeluxe);
        }
        else
            return String.format("E%02d", roomNumberExecutive);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
        for (Room room : rooms) {
            room.setBasePrice(basePrice);
        }
    }
    public void setPriceModifier(int day, double modifier) {
        if (day >= 1 && day <= 30) {
            this.priceModifiers[day - 1] = modifier;
        } else {
            System.out.println("Invalid day. Please enter a day between 1 and 30.");
        }
    }

    public double getPriceModifier(int day) {
        if (day >= 1 && day <= 30) {
            return this.priceModifiers[day - 1];
        } else {
            System.out.println("Invalid day. Please enter a day between 1 and 30.");
            return 1.0; // Default modifier
        }
    }

    public double[] getPriceModifiers() {
        return priceModifiers;
    }

    public boolean addRoom(RoomType roomType, int totalRooms) {
        for (int i = 0; i<totalRooms; i++) {

            if (rooms.size() >= MAX_ROOMS) {
                System.out.println("Maximum room limit reached.");
                return false;
            }

            String roomName = generateRoomName(roomType);
            rooms.add(new Room(roomName, basePrice, roomType, priceModifiers));
            System.out.println("Room " + roomName + " (" + roomType + ") added successfully.");
        }

        return true;
    }

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

    public Room findRoom(String roomName) {
        for (Room room : rooms) {
            if (room.getName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

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

    public boolean removeHotel() {
        if (reservations.isEmpty()) {
            return true;
        }
        return false;
    }

    public double calculateEarnings() {
        double total = 0;
        for (Reservation reservation : reservations) {
            total += reservation.calculateTotalPrice();
        }
        return total;
    }

    public int roomCount() {
        int count = 0;
        for (Room room : rooms) {
            count++;
        }
        return count;
    }

    public int remainingRoomCount(){
        return 50 - roomCount();
    }
}