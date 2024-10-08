import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageHotelController implements ActionListener {
    private ManageHotelView view;
    private HotelSystem hotelSystem;
    private String hotelName;
    private Hotel hotel;

    /* ManageHotelController Constructor
                    a. Purpose: Initializes the ManageHotelController with the view, hotel system, and hotel name.
                    b. Parameters:
                             - view: ManageHotelView
                             - hotelSystem: HotelSystem
                             - hotelName: String
                    c. Return type: None
    */
    public ManageHotelController(ManageHotelView view, HotelSystem hotelSystem, String hotelName) {
        this.view = view;
        this.hotelSystem = hotelSystem;
        this.hotelName = hotelName;
        this.hotel = hotelSystem.findHotel(hotelName);
        view.setActionListener(this);
    }
    /* updateRoomCounts Method
                    a. Purpose: Updates the room count labels in the view with the current room counts.
                    b. Parameters: None
                    c. Return type: void
    */
    private void updateRoomCounts() {
        int overallRoomCount = hotel.getRoomCount();
        int standardRoomCount = hotel.getRoomCountType(RoomType.STANDARD);
        int deluxeRoomCount = hotel.getRoomCountType(RoomType.DELUXE);
        int executiveRoomCount = hotel.getRoomCountType(RoomType.EXECUTIVE);
        if(overallRoomCount == 50){
            view.showErrorDialog("Maximum Amount of Rooms Reached!");
        }
        view.updateRoomCountLabels(overallRoomCount, standardRoomCount, deluxeRoomCount, executiveRoomCount);
    }
    /* actionPerformed Method
                    a. Purpose: Handles action events triggered by the view's buttons.
                    b. Parameters:
                             - e: ActionEvent
                    c. Return type: void
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        List<String> hotelNames;
        String hotelNewName, roomName;
        Room roomToRemove;
        double newPrice;
        int response;
        hotel = hotelSystem.findHotel(hotelName);
        int overallRoomCount;
        int standardRoomCount;
        int deluxeRoomCount;
        int executiveRoomCount;
        switch (e.getActionCommand()) {
            case "Change Hotel Name":
                hotelNewName = JOptionPane.showInputDialog("Enter hotel name:");
                if (hotelNewName.trim().isEmpty()) {
                view.showErrorDialog("Please fill out the field.");
                break;
                }
                response = JOptionPane.showConfirmDialog(null, "Do you want to change the name to: " +hotelNewName+ "?", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    if (hotelNewName != null && !hotelNewName.trim().isEmpty()) {
                        hotel.setName(hotelNewName);
                        hotelName = hotelNewName;
                        view.showConfirmationDialog("Hotel Name Successfully Changed");
                    }
                    else {
                    }
                }
                break;
            //---------------------------------ROOM SECTION----------------------------
            case "Add Room":
                overallRoomCount = hotel.getRoomCount();
                standardRoomCount = hotel.getRoomCountType(RoomType.STANDARD);
                deluxeRoomCount = hotel.getRoomCountType(RoomType.DELUXE);
                executiveRoomCount = hotel.getRoomCountType(RoomType.EXECUTIVE);
                view.showAddRoomPanel(overallRoomCount, standardRoomCount, deluxeRoomCount, executiveRoomCount, this);

                break;
            case "Add Standard Room":
                hotel.addRoom(RoomType.STANDARD, 1);
                updateRoomCounts();
                break;
            case "Add Deluxe Room":
                hotel.addRoom(RoomType.DELUXE, 1);
                updateRoomCounts();
                break;
            case "Add Executive Room":
                hotel.addRoom(RoomType.EXECUTIVE, 1);
                updateRoomCounts();
                break;
            //---------------------------------
            case "Remove Room":
                List<Room> availableRooms = hotel.getAvailableRooms();
                roomToRemove = view.showRemoveRoomDialog(availableRooms);
                if (roomToRemove != null) {
                    roomName = roomToRemove.getName();
                    response = JOptionPane.showConfirmDialog(null, "Do you want to remove the room: " +roomName+ "?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        if (hotel.removeRoomStraight(roomName)) {
                            view.showConfirmationDialog("Room removed successfully.");
                        } else {
                            view.showErrorDialog("Failed to remove room.");
                        }
                        updateRoomCounts();
                    }
                }
                break;
            case "Update Base Price":
                if (hotel.getReservations().isEmpty()) {
                    newPrice = view.showUpdateBasePriceDialog();
                    if (newPrice >= 100.0) {
                        response = JOptionPane.showConfirmDialog(null, "Do you want to change the base price?", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            hotel.setBasePrice(newPrice);
                            view.showConfirmationDialog("Base price updated.");
                        }
                    } else {
                        view.showErrorDialog("Base price must be >= 100.0");
                    }
                } else {
                    view.showErrorDialog("Cannot update base price with active reservations.");
                }
                break;
            case "Remove Reservation":
                Room roomWithReservations = view.showRoomsWithReservationsDialog(hotel.getRoomsWithReservations());
                if (roomWithReservations != null) {
                    Reservation reservationToRemove = view.showReservationsDialog(roomWithReservations.getReservations());
                    if (reservationToRemove != null) {
                        response = JOptionPane.showConfirmDialog(null, "Do you want to remove this reservation?", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            hotel.removeReservationStraight(reservationToRemove.getGuestName(), reservationToRemove.getCheckInDate(), reservationToRemove.getCheckOutDate());
                            view.showConfirmationDialog("Reservation removed successfully.");
                        }
                    }
                }
                break;
            case "View Hotel Information":
                if (hotel != null) {
                    view.showHotelInformation(hotel);
                } else {
                    view.showErrorDialog("Hotel not found.");
                }
                break;
            case "View Reservations":
                if (hotel != null) {
                    Room selectedRoom = view.showRoomsWithReservationsDialog(hotel.getRoomsWithReservations());
                    if (selectedRoom != null) {
                        Reservation selectedReservation = view.showReservationsDialog(selectedRoom);
                        if (selectedReservation != null) {
                            view.showReservationDetails(selectedReservation);
                        }
                    }
                } else {
                    view.showErrorDialog("Hotel not found.");
                }
                break;
            case "Set Date Price Modifier":
                double[] priceModifiers = hotel.getPriceModifiers();
                int selectedDay = view.showDatePriceModifierDialog(priceModifiers);
                if (selectedDay != -1) {
                    double newModifier = view.showNewPriceModifierDialog();
                    if (newModifier >= 0) {
                        response = JOptionPane.showConfirmDialog(null, "Do you want to change the price of Day: " + selectedDay + "?", "Confirm", JOptionPane.YES_NO_OPTION);
                        if (response == JOptionPane.YES_OPTION) {
                            hotel.setPriceModifier(selectedDay, newModifier);
                            view.showConfirmationDialog("Price modifier set for day " + selectedDay);
                        }
                    } else {
                        view.showErrorDialog("Invalid price modifier entered.");
                    }
                }
                break;
            case "Remove Hotel":
                response = JOptionPane.showConfirmDialog(null, "Do you want to remove this hotel? ", "Confirm", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    if (hotelSystem.removeHotel(hotelName)) {
                        JOptionPane.showMessageDialog(null, "Hotel removed successfully.");
                        hotelName = null;  // Reset hotelName since it's removed
                        hotel = null;      // Reset hotel reference
                    } else {
                        JOptionPane.showMessageDialog(null, "Hotel not found. Failed to remove hotel.");
                    }
                }

            case "Back":
                view.dispose(); // Close the ManageHotelView
                MainMenuView mainMenuView = new MainMenuView();
                new MainMenuController(mainMenuView, hotelSystem);
                mainMenuView.setVisible(true);
                break;
        }
    }
}
