import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final HotelSystem hotelSystem = new HotelSystem();

    public static void main(String[] args) {

        HotelSystem hotelSystem = new HotelSystem();
        MainMenuView view = new MainMenuView();
        MainMenuController controller = new MainMenuController(view, hotelSystem);

        int choice;
        boolean exit = false;

        while (!exit) { //------------------------------------------------------HOTEL RESERVATION SYSTEM------------------------------------------------
            displayMainMenu();
            choice = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (choice) {
                case 1:
                    createHotel();
                    break;
                case 2:
                    viewHotel();
                    break;
                case 3:
                    hotelSystem.listHotels();
                    manageHotel();
                    break;
                case 4:
                    hotelSystem.listHotels();
                    simulateBooking();
                    break;
                case 5:
                    hotelSystem.listHotels();
                    break;
                case 6:
                    removeHotel();
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("Exiting Hotel Reservation System. Goodbye!");
    }
    /* displayMainMenu Method
          a. Purpose: Displays the main menu of the hotel reservation system.
          b. Parameters: None
          c. Return type: void
      */
    private static void displayMainMenu() {
        System.out.println("\n>=====[Hotel Reservation System]=====<");
        System.out.println();
        System.out.println("          [1] Create Hotel");
        System.out.println("          [2] View Hotel");
        System.out.println("          [3] Manage Hotel");
        System.out.println("          [4] Simulate Booking");
        System.out.println("          [5] List Hotels");
        System.out.println("          [6] Remove Hotel");
        System.out.println("          [7] Exit");
        System.out.println();
        System.out.print("Enter your choice: ");
    }
    /* createHotel Method
      a. Purpose: Prompts the user to enter a hotel name and creates a new hotel in the system.
      b. Parameters: None
      c. Return type: void
  */
    private static void createHotel() {
        System.out.print("Enter hotel name: ");
        String hotelName = scanner.nextLine();
        hotelSystem.createHotel(hotelName);
    }
    /* viewHotel Method
          a. Purpose: Prompts the user to enter a hotel name and displays the high-level or low-level information of the hotel.
          b. Parameters: None
          c. Return type: void
      */
    private static void viewHotel() {
        String hotelName;
        int option;
        boolean exit = false;

        hotelSystem.listHotels();
        System.out.print("Enter hotel name to view: ");
        hotelName = scanner.nextLine();
        Hotel hotel = hotelSystem.findHotel(hotelName);

        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        System.out.println("\nSelect option:");
        System.out.println("1. View High-Level Information");
        System.out.println("2. View Low-Level Information");
        System.out.print("\nEnter your choice: ");
        option = Integer.parseInt(scanner.nextLine());

        switch (option) {
            case 1:
                hotelSystem.viewHotel(hotelName);
                break;
            case 2:
                viewDetailedHotelInformation(hotel);
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
    /* viewDetailedHotelInformation Method
          a. Purpose: Displays detailed information of a hotel including available and booked rooms, room information, and reservation information.
          b. Parameters:
             - hotel (Hotel): The hotel object whose information is to be displayed.
          c. Return type: void
      */
    private static void viewDetailedHotelInformation(Hotel hotel) {

        int detailChoice;
        boolean exit = false;
        while(!exit){ //------------------------------------------------------VIEW HOTEL INFORMATION------------------------------------------------
            System.out.println("\n>=======[Detailed Hotel Information]=======<\n");
            System.out.println("[1] Total Available and Booked Rooms for a Date");
            System.out.println("            [2] Room Information");
            System.out.println("        [3] Reservation Information");
            System.out.println("                 [4] Exit");
            System.out.print("\nEnter your choice: ");
            detailChoice = Integer.parseInt(scanner.nextLine());

            switch (detailChoice) {
                case 1:
                    totalAvailableAndBookedRooms(hotel);
                    break;
                case 2:
                    roomInformation(hotel);
                    break;
                case 3:
                    reservationInformation(hotel, hotel.getName());
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
    /* totalAvailableAndBookedRooms Method
          a. Purpose: Displays the total number of available and booked rooms for a specific date in the hotel.
          b. Parameters:
             - hotel (Hotel): The hotel object whose room availability is to be checked.
          c. Return type: void
      */
    private static void totalAvailableAndBookedRooms(Hotel hotel) {
        int date, availableRooms, bookedRooms;

        System.out.print("\nEnter the date to check (1-30): ");
        date = Integer.parseInt(scanner.nextLine());
        if(date >= 31 || date < 1)
        {
            System.out.println("Invalid date.");
            return;
        }
        availableRooms = 0;
        bookedRooms = 0;

        for (Room room : hotel.getRooms()) {
            if (room.isAvailable(date, date + 1)) {
                availableRooms++;
            } else {
                bookedRooms++;
            }
        }

        System.out.println("\nTotal available rooms on day " + date + ": " + availableRooms);
        System.out.println("Total booked rooms on day " + date + ": " + bookedRooms);
    }
    /* roomInformation Method
          a. Purpose: Displays detailed information of a specific room in the hotel including its availability.
          b. Parameters:
             - hotel (Hotel): The hotel object whose room information is to be displayed.
          c. Return type: void
      */
    private static void roomInformation(Hotel hotel) {
        String roomName;
        Room room;
        boolean[] availability;

        hotelSystem.viewHotelRooms(hotel.getName());
        System.out.print("\nEnter room name to view details: ");
        roomName = scanner.nextLine();
        room = hotel.findRoom(roomName);

        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        availability = room.getAvailability();
        System.out.println("Room Name: " + room.getName());
        System.out.println("Base Price: " + room.getBasePrice());
        System.out.println("Room Type: " + room.getRoomType()); // Added Room Type Information
        System.out.println("\nAvailability:");
        for (int day = 1; day <= availability.length; day++) {
            System.out.printf("Day %2d: %s\n", day, availability[day - 1] ? "Available" : "Booked");
        }
    }
    /* manageHotel Method
          a. Purpose: Allows the user to manage a specific hotel including changing its name, adding/removing rooms,
                      updating the base price, removing reservations, and viewing hotel/reservation information.
          b. Parameters: None
          c. Return type: void
      */
    private static void manageHotel() {

        String hotelName, response;
        Hotel hotel;
        int choice;
        String newHotelName, roomToRemove;
        double newPrice;
        boolean back;
        System.out.print("\nEnter hotel name to manage: ");
        hotelName = scanner.nextLine();
        hotel = hotelSystem.findHotel(hotelName);
        RoomType roomType;
        int roomChoice;
        int totalRooms;

        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        back = false;
        while (!back) { //------------------------------------------------------MANAGE HOTEL------------------------------------------------
            displayManageHotelMenu();
            choice = Integer.parseInt(scanner.nextLine());
            System.out.println();
            switch (choice) {
                case 1:
                    System.out.print("Enter new hotel name: ");
                    newHotelName = scanner.nextLine();
                    if (isHotelNameUnique(newHotelName)) {
                        System.out.print("Do you want to change this hotel name? (Y/N): ");
                        response = scanner.nextLine();
                        if (response.equalsIgnoreCase("N")) {
                            break;
                        }
                        else if(!response.equalsIgnoreCase("Y")) {
                            System.out.print("\nInvalid choice. Exiting modification.");
                            break;
                        }
                        hotel.setName(newHotelName);
                        hotelName = newHotelName;
                        System.out.println("Hotel name updated.");
                    } else {
                        System.out.println("Hotel name must be unique. Failed to update hotel name.");
                    }
                    break;
                case 2:
                    // Add a single room with specific details
                    if(hotel.remainingRoomCount() != 0){
                        System.out.println("[1] STANDARD");
                        System.out.println("[2] DELUXE");
                        System.out.println("[3] EXECUTIVE");
                        System.out.print("Select room type: ");
                        roomChoice = Integer.parseInt(scanner.nextLine());
                        if(roomChoice == 1){
                            roomType = RoomType.STANDARD;
                        }
                        else if(roomChoice == 2){
                            roomType = RoomType.DELUXE;
                        }
                        else if(roomChoice == 3){
                            roomType = RoomType.EXECUTIVE;
                        }
                        else{
                            System.out.println("Invalid choice. Exiting modification.");
                            break;
                        }
                        System.out.print("How many rooms (1 - " + hotel.remainingRoomCount() + "): ");
                        totalRooms = Integer.parseInt(scanner.nextLine());
                        if(totalRooms > hotel.remainingRoomCount()){
                            System.out.println("Input Out of Bounds!");
                            break;
                        }
                        if (hotel.addRoom(roomType, totalRooms)) {
                        } else {
                            System.out.println("Failed to add room.");
                        }
                    }
                    else{
                        System.out.println("Maximum Amount of Rooms Reached!");
                    }
                    break;
                case 3:
                    hotelSystem.viewHotelRooms(hotelName);
                    System.out.print("\nEnter room name to remove: ");
                    roomToRemove = scanner.nextLine();
                    if (hotel.removeRoom(roomToRemove)) {
                        System.out.println("Room removed successfully.");
                    } else {
                        System.out.println("Failed to remove room.");
                    }
                    break;
                case 4:
                    if(hotel.getReservations().isEmpty()){
                        System.out.print("Enter new base price: ");
                        newPrice = Double.parseDouble(scanner.nextLine());
                        if (newPrice >= 100.0) {
                            System.out.print("\nDo you want to change the base price? (Y/N): ");
                            response = scanner.nextLine();
                            if (response.equalsIgnoreCase("N")) {
                                break;
                            }
                            else if(!response.equalsIgnoreCase("Y")) {
                                System.out.print("\nInvalid choice. Exiting modification.");
                                break;
                            }
                            hotel.setBasePrice(newPrice);
                            System.out.println("Base price updated.");
                        } else {
                            System.out.println("Base price must be >= 100.0");
                        }
                    }
                    else{
                        System.out.println("Cannot change base price. There are existing reservations.");
                    }
                    break;
                case 5:
                    removeReservation(hotel);
                    break;
                case 6:
                    hotelSystem.viewHotel(hotelName);
                    break;
                case 7:
                    reservationInformation(hotel, hotelName);
                    break;
                case 8:
                    setDatePriceModifier(hotel);
                    break;
                case 9:
                    removeCurrentHotel(hotelName);
                    break;
                case 10:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
    /* removeReservation Method
          a. Purpose: Removes a reservation.
          b. Parameters:
             - hotel (Hotel): The hotel object whose room information is to be displayed.
          c. Return type: void
      */
    private static void removeReservation(Hotel hotel) {

        int checkInDate, checkOutDate;
        String guestName;

        System.out.print("Enter guest name: ");
        guestName = scanner.nextLine();
        System.out.print("Enter check-in date (1-30): ");
        checkInDate = Integer.parseInt(scanner.nextLine());
        if(checkInDate >= 31 || checkInDate < 1){
            System.out.println("\nError Check-in date is invalid.");
            return;
        }
        System.out.print("Enter check-out date (2-31): ");
        checkOutDate = Integer.parseInt(scanner.nextLine());
        if(checkOutDate >= 32 || checkOutDate < 2  || checkInDate > checkOutDate){
            System.out.println("\nError Check-out date is invalid.");
            return;
        }

        if (hotel.removeReservation(guestName, checkInDate, checkOutDate)) {
            System.out.println("Reservation removed successfully.");
        } else {
            System.out.println("Failed to remove reservation.");
        }
    }
    /* displayManageHotelMenu Method
        a. Purpose: Displays the menu for managing a specific hotel.
        b. Parameters: None
        c. Return type: void
    */
    private static void displayManageHotelMenu() {
        System.out.println("\n>===========[Manage Hotel]===========<");
        System.out.println("\n        [1] Change Hotel Name");
        System.out.println("        [2] Add Room");
        System.out.println("        [3] Remove Room");
        System.out.println("        [4] Update Base Price");
        System.out.println("        [5] Remove Reservation");
        System.out.println("        [6] View Hotel Information");
        System.out.println("        [7] View Reservations");
        System.out.println("        [8] Set Date Price Modifier");
        System.out.println("        [9] Remove Hotel");
        System.out.println("        [10] Back");
        System.out.print("\nEnter your choice: ");
    }
    /* simulateBooking Method
        a. Purpose: Simulates a booking for the provided hotel.
        b. Parameters: None
        c. Return type: void
    */
    private static void simulateBooking() {

        Hotel hotel;
        int checkInDate, checkOutDate;
        String guestName, hotelName, roomName, response;
        Room room;
        Reservation reservation;
        boolean[] availability;
        String applyDiscount;
        String discountCode;

        System.out.print("Enter hotel name: ");
        hotelName = scanner.nextLine();
        hotel = hotelSystem.findHotel(hotelName);
        if (hotel == null) {
            System.out.println("Hotel not found.");
            return;
        }

        hotelSystem.viewHotelRooms(hotelName);
        System.out.print("\nEnter room name to view details: ");
        roomName = scanner.nextLine();
        room = hotel.findRoom(roomName);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        availability = room.getAvailability();
        System.out.println("Room Name: " + room.getName());
        System.out.println("\nAvailability:");
        for (int day = 1; day <= availability.length; day++) {
            System.out.printf("Day %2d: %s\n", day, availability[day - 1] ? "Available" : "Booked");
        }

        System.out.print("\nDo you want to book this room? (Y/N): ");
        response = scanner.nextLine();
        if (!response.equalsIgnoreCase("Y")) {
            return;
        }

        System.out.print("Enter guest name: ");
        guestName = scanner.nextLine();
        System.out.print("Enter check-in date (1-30): ");
        checkInDate = Integer.parseInt(scanner.nextLine());
        if(checkInDate >= 31 || checkInDate < 1){
            System.out.println("\nError Check-in date is invalid.");
            return;
        }
        System.out.print("Enter check-out date (2-31): ");
        checkOutDate = Integer.parseInt(scanner.nextLine());
        if(checkOutDate >= 32 || checkOutDate < 2 || checkInDate > checkOutDate){
            System.out.println("\nError Check-out date is invalid.");
            return;
        }

        if (!room.isAvailable(checkInDate, checkOutDate)) {
            System.out.println("Room is not available for the selected dates.");
            return;
        }

        System.out.print("Would you like to input a discount code? (Y/N): ");
        applyDiscount = scanner.nextLine();
        discountCode = "";
        if (applyDiscount.equalsIgnoreCase("Y")) {
            System.out.print("Enter discount code: ");
            discountCode = scanner.nextLine();
        }
        else{
            discountCode = "NONE";
        }
        reservation = new Reservation(guestName, checkInDate, checkOutDate, room, discountCode);
        hotel.getReservations().add(reservation);
        System.out.println("Reservation created successfully.");
        System.out.println("Reservation Details:");
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room: " + room.getName());
        System.out.println("Room Type: " + room.getRoomType());
        System.out.println("Check-in: " + checkInDate);
        System.out.println("Check-out: " + checkOutDate);
        System.out.println("Total Price: " + reservation.calculateTotalPrice());
    }

    /* removeHotel Method
        a. Purpose: Prompts the user to enter a hotel name and removes the hotel from the system.
        b. Parameters: None
        c. Return type: void
    */
    private static void removeHotel() {
        String hotelName;
        System.out.print("Enter hotel name to remove: ");
        hotelName = scanner.nextLine();

        if (hotelSystem.removeHotel(hotelName)) {
            System.out.println("Hotel removed successfully.");
        } else {
            System.out.println("Hotel not found.");
        }
    }

    private static boolean isHotelNameUnique(String newHotelName) {
        return hotelSystem.findHotel(newHotelName) == null;
    }
    
    /* reservationInformation Method
          a. Purpose: Displays the reservation information for a specific hotel.
          b. Parameters:
             - hotelName (String): Name of the hotel being modified.
          c. Return type: void
      */
    private static void reservationInformation(Hotel hotel, String hotelName) {

        String roomName;
        Room room;
        int index, reservationIndex;
        Reservation selectedReservation;

        hotelSystem.viewHotelRooms(hotelName);

        System.out.print("\nEnter room name to view reservations: ");

        roomName = scanner.nextLine();
        room = hotel.findRoom(roomName);

        if (room == null) {
            System.out.println("\nRoom not found.\n");
            return;
        }

        if (room.getReservations().isEmpty()) {
            System.out.println("\nNo reservations found for this room.\n");
            return;
        }

        System.out.println("\nReservations for Room: " + room.getName());
        index = 1;
        for (Reservation reservation : room.getReservations()) {
            System.out.printf("%d. Guest: %s, Check-in: %d, Check-out: %d, Total Price: %.2f\n",
                    index++,
                    reservation.getGuestName(),
                    reservation.getCheckInDate(),
                    reservation.getCheckOutDate(),
                    reservation.calculateTotalPrice());
        }

        System.out.print("Select reservation to view details (1-" + room.getReservations().size() + "): ");
        reservationIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (reservationIndex < 0 || reservationIndex >= room.getReservations().size()) {
            System.out.println("Invalid reservation selection.");
            return;
        }

        selectedReservation = room.getReservations().get(reservationIndex);
        System.out.println("\nReservation Details:");
        System.out.println("Guest Name: " + selectedReservation.getGuestName());
        System.out.println("Room: " + selectedReservation.getRoom().getName());
        System.out.println("Room Type: " + selectedReservation.getRoom().getRoomType());
        System.out.println("Check-in Date: " + selectedReservation.getCheckInDate());
        System.out.println("Check-out Date: " + selectedReservation.getCheckOutDate());
        System.out.println("Total Price: " + selectedReservation.calculateTotalPrice());
        System.out.println("\nPrice Breakdown:");
        for (int day = selectedReservation.getCheckInDate(); day < selectedReservation.getCheckOutDate(); day++) {
            System.out.printf("Day %d: %.2f\n", day, room.getBasePrice());
        }
    }
    /* removeCurrentHotel Method
        a. Purpose: Similar to the removeHotel but is utilized during the "Manage Hotel Section"
        b. Parameters:
           - hotelName (String): Name of the hotel being modified.
        c. Return type: void
    */
    private static void removeCurrentHotel(String hotelName) {
        String response;
        System.out.print("Do you want to remove this hotel? [" + hotelName + "] (Y/N): ");
        response = scanner.nextLine();
        if (response.equalsIgnoreCase("N")) {
            return;
        }
        else if(!response.equalsIgnoreCase("Y")) {
            System.out.print("\nInvalid choice. Exiting modification.");
            return;
        }
        if (hotelSystem.removeHotel(hotelName)) {
            System.out.println("Hotel removed successfully.");
        } else {
            System.out.println("Failed to remove hotel.");
        }
    }

    private static void setDatePriceModifier(Hotel hotel) {
        int day;
        double modifier;

        System.out.print("Enter the day of the month (1-30): ");
        day = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter the price modifier (e.g., 1.1 for 110%, 0.9 for 90%): ");
        modifier = Double.parseDouble(scanner.nextLine());
        if(modifier < 0){
            System.out.println("Invalid Modifier");
            return;
        }
        hotel.setPriceModifier(day, modifier);
        System.out.println("Price modifier set for day " + day);
    }
}


