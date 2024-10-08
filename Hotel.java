
import java.util.*;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Reservation> reservations;
    private static final int MAX_ROOMS = 50;
    private double basePrice = 1299.0; // Default base price
    private static final Scanner scanner = new Scanner(System.in);
    private double[] priceModifiers;
    private Queue<Integer> availableStandardNumbers;
    private Queue<Integer> availableDeluxeNumbers;
    private Queue<Integer> availableExecutiveNumbers;
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
        this.priceModifiers = new double[30];
        Arrays.fill(this.priceModifiers, 1.0); // Default modifier is 100%
        this.availableStandardNumbers = new PriorityQueue<>();
        this.availableDeluxeNumbers = new PriorityQueue<>();
        this.availableExecutiveNumbers = new PriorityQueue<>();
        addDefaultRoom();
    }
    /* addDefaultRoom Method
            a. Purpose: Adds a default room to the hotel.
            b. Parameters: None
            c. Return type: None
    */
    private void addDefaultRoom() {
        Room defaultRoom = new Room("S01", basePrice, RoomType.STANDARD, priceModifiers);
        rooms.add(defaultRoom);
    }
    /* generateRoomName Method
         a. Purpose: Generates a unique room name for new rooms.
         b. Parameters: roomType (RoomType): Type of the room.
         c. Return type: String - the generated room name.
    */
    private String generateRoomName(RoomType roomType) {
        int roomNumber;
        switch (roomType) {
            case STANDARD:
                roomNumber = availableStandardNumbers.isEmpty() ? getNextRoomNumber(RoomType.STANDARD) : availableStandardNumbers.poll();
                return String.format("S%02d", roomNumber);
            case DELUXE:
                roomNumber = availableDeluxeNumbers.isEmpty() ? getNextRoomNumber(RoomType.DELUXE) : availableDeluxeNumbers.poll();
                return String.format("D%02d", roomNumber);
            case EXECUTIVE:
                roomNumber = availableExecutiveNumbers.isEmpty() ? getNextRoomNumber(RoomType.EXECUTIVE) : availableExecutiveNumbers.poll();
                return String.format("E%02d", roomNumber);
            default:
                throw new IllegalArgumentException("Unknown Room Type");
        }
    }
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
    /* setPriceModifier Method
    a. Purpose: Sets the base price modifier.
    b. Parameters:
        - day (int): Amount of days booked.
        - basePrice (modifier): The new base price for rooms.
    c. Return type: None
    */
    public void setPriceModifier(int day, double modifier) {
        if (day >= 1 && day <= 30) {
            this.priceModifiers[day - 1] = modifier;
        } else {
            System.out.println("Invalid day. Please enter a day between 1 and 30.");
        }
    }
    /* setPriceModifier Method
        a. Purpose: Gets the next room number to be added.
        b. Parameters:
            - roomType (RoomType): Type of the room
        c. Return type: int
    */
    private int getNextRoomNumber(RoomType roomType) {
        int roomNumber = 1;
        int currentRoomNumber;
        for (Room room : rooms) {
            RoomType type = room.getRoomType();
            if (type == roomType) {
                currentRoomNumber = Integer.parseInt(room.getName().substring(1));
                if (currentRoomNumber >= roomNumber) {
                    roomNumber = currentRoomNumber + 1;
                }
            }
        }
        return roomNumber;
    }
    /* setPriceModifier Method
            a. Purpose: Getter for PriceModifiers
            b. Parameters:
                - None
            c. Return type: double[]
        */
    public double[] getPriceModifiers() {
        return priceModifiers;
    }
    /* addRoom Method
        a. Purpose: Adds a new room to the hotel.
        b. Parameters: None
        c. Return type: boolean - true if the room is successfully added, false otherwise.
    */
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
    /* releaseRoomNUmber Method
        a. Purpose: Releases the next room number to be modified. (Used if a room is removed in between)
        b. Parameters: room (Room)
        c. Return type: void
    */
    private void releaseRoomNumber(Room room) {
        int roomNumber = Integer.parseInt(room.getName().substring(1));
        RoomType roomType = room.getRoomType();
        switch (roomType) {
            case STANDARD:
                availableStandardNumbers.add(roomNumber);
                break;
            case DELUXE:
                availableDeluxeNumbers.add(roomNumber);
                break;
            case EXECUTIVE:
                availableExecutiveNumbers.add(roomNumber);
                break;
        }
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
            } else if (!response.equalsIgnoreCase("Y")) {
                System.out.print("\nInvalid choice. Exiting modification.\n");
                return false;
            }
            rooms.remove(roomToRemove);
            releaseRoomNumber(roomToRemove);
            return true;
        } else if (roomToRemove != null && !roomToRemove.getReservations().isEmpty()) {
            System.out.println("Room cannot be removed. It has active reservations.");
        } else {
            System.out.println("Invalid Input");
        }
        return false;
    }


    /* removeRoomStraight Method
        a. Purpose: Similar to method above, doesn't ask for a prompt.
        b. Parameters:
            - roomName (String): The name of the room to remove.
        c. Return type: boolean - true if the room is successfully removed, false otherwise.
    */
    public boolean removeRoomStraight(String roomName) {
        Room roomToRemove = findRoom(roomName);
        if (roomToRemove != null && roomToRemove.getReservations().isEmpty()) {
            rooms.remove(roomToRemove);
            releaseRoomNumber(roomToRemove);
            return true;
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
    /*
    getAvailableRooms Method
            a. Purpose: Creates a List of rooms with no reservation
            b. Parameters:
                -
            c. Return type: List<Room>: List of rooms with no reservation
    */
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getReservations().isEmpty()) {

                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    /* guiListRooms Method
                a. Purpose: Creates a List of room names
                b. Parameters:
                    -
                c. Return type: List<String>: List of room names
    */
    public List<String> guiListRooms() {
        List<String> roomNames = new ArrayList<>();
        for (Room room : rooms) {
            roomNames.add(room.getName());
        }
        return roomNames;
    }
    /*
        getRoomsWithReservations Method
                a. Purpose: Creates a List of rooms with reservation
                b. Parameters:
                    -
                c. Return type: List<Room>: List of rooms with reservation
        */
    public List<Room> getRoomsWithReservations() {
        List<Room> roomsWithReservations = new ArrayList<>();
        for (Room room : rooms) {
            if (!room.getReservations().isEmpty()) {
                roomsWithReservations.add(room);
            }
        }
        return roomsWithReservations;
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
    /* removeReservation Method
            a. Purpose: Removes a reservation from the hotel without an asking prompt.
            b. Parameters:
                - guestName (String): The name of the guest with the reservation.
                - checkInDate (int): The check-in date of the reservation.
                - checkOutDate (int): The check-out date of the reservation.
            c. Return type: boolean - true if the reservation is successfully removed, false otherwise.
        */
    public boolean removeReservationStraight(String guestName, int checkInDate, int checkOutDate) {
        String response;
        Reservation reservationToRemove = findReservation(guestName, checkInDate, checkOutDate);

        if (reservationToRemove != null) {
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
    /* getRoomCount Method
            a. Purpose: Gets the amount of rooms in the hotel.
            b. Parameters: None
            c. Return type: int
    */
    public int getRoomCount() {
        int count = 0;
        for (Room room : rooms) {
            count++;
        }
        return count;
    }
    /* getRoomCountType Method
            a. Purpose: Gets the amount of roomType rooms in the hotel.
            b. Parameters: None
            c. Return type: int
    */
    public int getRoomCountType(RoomType type) {
        int count = 0;
        for (Room room : getRooms()) {
            if(room.getRoomType() == type){
                count++;
            }
        }
        return count;
    }
    /* remainingRoomCount Method
            a. Purpose: Gets the remaining amounts of rooms
            b. Parameters: None
            c. Return type: int
    */
    public int remainingRoomCount(){
        return 50 - getRoomCount();
    }

    /* getAllReservations Method
            a. Purpose: Gets the reservations found in the hotel
            b. Parameters: None
            c. Return type: List<Reservation>
    */
    public List<Reservation> getAllReservations() {
        List<Reservation> allReservations = new ArrayList<>();
        for (Room room : rooms) {
            allReservations.addAll(room.getReservations());
        }
        return allReservations;
    }
}