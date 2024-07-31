// MainMenuController.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class MainMenuController implements ActionListener {
    private MainMenuView view;
    private HotelSystem hotelSystem;

    /* MainMenuController Constructor
                    a. Purpose: Initializes the MainMenuController with the given view and hotel system, setting up the action listeners for the view.
                    b. Parameters:
                             - view: MainMenuView
                             - hotelSystem: HotelSystem
                    c. Return type: None
    */

    public MainMenuController(MainMenuView view, HotelSystem hotelSystem) {
        this.view = view;
        this.hotelSystem = hotelSystem;

        view.setActionListener(this);
    }
    /* actionPerformed Method
                        a. Purpose: Handles action events triggered by user interactions with the main menu, performing corresponding operations based on the action command.
                        b. Parameters:
                                 - e: ActionEvent
                        c. Return type: void
        */
    @Override
    public void actionPerformed(ActionEvent e) {
        Hotel hotel;
        Room rm;
        String hotelName, selectedHotel, roomName;
        List<String> hotelNames, roomNames;
        switch (e.getActionCommand()) {
            //Create Hotel Controller
            case "Create Hotel":
                hotelName = JOptionPane.showInputDialog("Enter hotel name:");

                if (hotelName != null && !hotelName.trim().isEmpty()) {
                    if (hotelSystem.createHotel(hotelName)) {
                        JOptionPane.showMessageDialog(null, "Hotel created successfully.\nA default STANDARD room named 'S01' has been added to the hotel.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Hotel name must be unique. Failed to create hotel.");
                    }
                } else if (hotelName != null) {
                    view.showErrorDialog("Please fill out the field.");
                    break;
                }
                break;
            case "View Hotel":
                hotelNames = hotelSystem.guiListHotels();
                if (hotelNames.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hotels found.");
                }
                else {
                    hotelName = view.showSelectHotelDialog(hotelNames, "View Hotel");
                    if (hotelName != null) {
                        String infoLevel = view.showInfoLevelDialog();
                        if (infoLevel != null) {
                            if (infoLevel.equals("High-Level Information")) {
                                hotel = hotelSystem.findHotel(hotelName);
                                StringBuilder info = new StringBuilder();
                                info.append("Hotel Name: ").append(hotel.getName()).append("\n");
                                info.append("Total Rooms: ").append(hotel.getRooms().size()).append("\n");
                                info.append("Estimated Earnings: ").append(hotel.calculateEarnings()).append("\n");
                                info.append("\nRooms: \n");

                                info.append("\nSTANDARD ROOMS:\n");
                                for (Room room : hotel.getRooms()) {
                                    if (room.getRoomType() == RoomType.STANDARD) {
                                        info.append(" - ").append(room.getName()).append(" | Base Price: ").append(room.calculatePrice(room.getBasePrice(), room.getRoomType())).append("\n");
                                    }
                                }
                                info.append("\nDELUXE ROOMS:\n");
                                for (Room room : hotel.getRooms()) {
                                    if (room.getRoomType() == RoomType.DELUXE) {
                                        info.append(" - ").append(room.getName()).append(" | Base Price: ").append(room.calculatePrice(room.getBasePrice(), room.getRoomType())).append("\n");
                                    }
                                }
                                info.append("\nEXECUTIVE ROOMS:\n");
                                for (Room room : hotel.getRooms()) {
                                    if (room.getRoomType() == RoomType.EXECUTIVE) {
                                        info.append(" - ").append(room.getName()).append(" | Base Price: ").append(room.calculatePrice(room.getBasePrice(), room.getRoomType())).append("\n");
                                    }
                                }
                                view.showMessageDialog(info.toString());
                            } else if (infoLevel.equals("Low-Level Information")) {
                                view.setVisible(false);
                                ViewHotelView viewHotelView = new ViewHotelView(hotelName);
                                new ViewHotelController(viewHotelView, hotelSystem, hotelName);
                                viewHotelView.setVisible(true);
                            }
                        }
                    }
                }
                break;
            case "Manage Hotel":
                hotelNames = hotelSystem.guiListHotels();
                if (hotelNames.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hotels found.");
                    break;
                }
                selectedHotel = view.showManageHotelDialog(hotelNames);
                if (selectedHotel != null) {
                view.setVisible(false);
                ManageHotelView manageHotelView = new ManageHotelView(selectedHotel);
                new ManageHotelController(manageHotelView, hotelSystem, selectedHotel);
                manageHotelView.setVisible(true);
                }

                break;
            case "Simulate Booking":
                hotelNames = hotelSystem.guiListHotels();
                if (hotelNames.isEmpty()) {
                    view.showErrorDialog("No hotels found.");
                    break;
                }
                hotelName = view.showHotelDialog(hotelNames);
                if (hotelName == null) {
                    break;
                }

                hotel = hotelSystem.findHotel(hotelName);
                roomNames = hotel.guiListRooms();
                if (roomNames.isEmpty()) {
                    view.showErrorDialog("No rooms found in this hotel.");
                    break;
                }
                roomName = view.showSelectRoomDialog(hotel.getRooms());
                if (roomName == null) {
                    break;
                }

                rm = hotel.findRoom(roomName);
                if (rm == null) {
                    view.showErrorDialog("Room not found.");
                    break;
                }

                boolean[] availability = rm.getAvailability();
                StringBuilder availabilityMessage = new StringBuilder();
                availabilityMessage.append("Room Name: ").append(rm.getName()).append("\n\nAvailability:\n");
                for (int day = 1; day <= availability.length; day++) {
                    availabilityMessage.append(String.format("Day %2d: %s\n", day, availability[day - 1] ? "Available" : "Booked"));
                }
                view.showMessageDialog(availabilityMessage.toString());

                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to book this room?", "Confirm Booking", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) {
                    break;
                }

                BookingDetails bookingDetails = view.showBookingDetailsDialog();
                if (bookingDetails == null) {
                    break;
                }
                if(bookingDetails.getGuestName().isEmpty()){
                    view.showErrorDialog("Please enter a guest name.");
                    break;
                }
                if (!rm.isAvailable(bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate())) {
                    view.showErrorDialog("Room is not available for the selected dates.");
                    break;
                }

                Reservation reservation = new Reservation(bookingDetails.getGuestName(), bookingDetails.getCheckInDate(), bookingDetails.getCheckOutDate(), rm, bookingDetails.getDiscountCode());
                hotel.getReservations().add(reservation);
                view.showMessageDialog("Reservation created successfully.\n" +
                        "Reservation Details:\n" +
                        "Guest Name: " + bookingDetails.getGuestName() + "\n" +
                        "Room: " + rm.getName() + "\n" +
                        "Room Type: " + rm.getRoomType() + "\n" +
                        "Check-in: " + bookingDetails.getCheckInDate() + "\n" +
                        "Check-out: " + bookingDetails.getCheckOutDate() + "\n" +
                        "Total Price: " + reservation.calculateTotalPrice());
                break;
            //Remove Hotel Controller
            case "Remove Hotel":
                hotelNames = hotelSystem.guiListHotels();
                if (hotelNames.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hotels found.");
                }
                else{
                    selectedHotel = view.showManageHotelDialog(hotelNames);
                    if (hotelSystem.removeHotel(selectedHotel)) {
                        JOptionPane.showMessageDialog(null, "Hotel removed successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Hotel not found. Failed to remove hotel.");
                    }
                }
                break;

            case "List Hotels":
                hotelNames = hotelSystem.guiListHotels();
                if (hotelNames.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hotels found.");
                } else {
                    StringBuilder hotelsList = new StringBuilder("Hotels:\n");
                    for (String h : hotelNames) {
                        hotelsList.append(h).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, hotelsList.toString());
                }
                break;
            case "Exit":
                confirm = JOptionPane.showConfirmDialog(view, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
        }
    }
}
