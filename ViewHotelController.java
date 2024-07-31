import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewHotelController implements ActionListener {
    private ViewHotelView view;
    private HotelSystem hotelSystem;
    private String hotelName;

    /* ViewHotelController Constructor
        a. Purpose: Initializes the ViewHotelController with the specified view, hotel system, and hotel name,
                    and sets the action listener for the view.
        b. Parameters:
                 - ViewHotelView view: The view component of the MVC architecture.
                 - HotelSystem hotelSystem: The system component managing hotel operations.
                 - String hotelName: The name of the hotel.
        c. Return type: Constructor (void)
    */

    public ViewHotelController(ViewHotelView view, HotelSystem hotelSystem, String hotelName) {
        this.view = view;
        this.hotelSystem = hotelSystem;
        this.hotelName = hotelName;
        view.setActionListener(this);
    }
    /* actionPerformed Method
        a. Purpose: Handles action events triggered by user interactions with the view, such as selecting
                    options from a menu or clicking buttons.
        b. Parameters:
                 - ActionEvent e: The event triggered by the user's action.
        c. Return type: void
    */

    @Override
    public void actionPerformed(ActionEvent e) {
        Hotel hotel = hotelSystem.findHotel(hotelName);
        if (hotel == null) {
            view.showErrorDialog("Hotel not found.");
            return;
        }

        switch (e.getActionCommand()) {
            case "Total Available and Booked Rooms":
                List<Room> rooms = hotel.getRooms();
                if (rooms.isEmpty()) {
                    view.showErrorDialog("No rooms available in the hotel.");
                    return;
                }
                String[] roomNames = rooms.stream().map(Room::getName).toArray(String[]::new);
                String selectedRoom = (String) JOptionPane.showInputDialog(null, "Select a room to view availability:",
                        "Room Selection", JOptionPane.QUESTION_MESSAGE, null, roomNames, roomNames[0]);

                if (selectedRoom != null) {
                    Room room = hotel.findRoom(selectedRoom);
                    if (room != null) {
                        StringBuilder availabilityInfo = new StringBuilder("Availability for room " + selectedRoom + ":\n");
                        boolean[] availability = room.getAvailability();
                        for (int day = 1; day <= availability.length; day++) {
                            availabilityInfo.append("Day ").append(day).append(": ").append(availability[day - 1] ? "VACANT" : "OCCUPIED").append("\n");
                        }
                        view.showMessageDialog(availabilityInfo.toString());
                    } else {
                        view.showErrorDialog("Room not found.");
                    }
                }
                break;

            case "Room Information":
                rooms = hotel.getRooms();
                if (rooms.isEmpty()) {
                    view.showErrorDialog("No rooms available in the hotel.");
                    return;
                }
                roomNames = rooms.stream().map(Room::getName).toArray(String[]::new);
                selectedRoom = (String) JOptionPane.showInputDialog(null, "Select a room to view details:",
                        "Room Selection", JOptionPane.QUESTION_MESSAGE, null, roomNames, roomNames[0]);

                if (selectedRoom != null) {
                    Room room = hotel.findRoom(selectedRoom);
                    if (room != null) {
                        StringBuilder info = new StringBuilder();
                        info.append("Room Name: ").append(room.getName()).append("\n");
                        info.append("Base Price: ").append(room.getBasePrice()).append("\n");
                        info.append("Room Type: ").append(room.getRoomType()).append("\n");
                        info.append("\nAvailability:\n");
                        boolean[] availability = room.getAvailability();
                        for (int day = 1; day <= availability.length; day++) {
                            info.append("Day ").append(day).append(": ").append(availability[day - 1] ? "Available" : "Booked").append("\n");
                        }
                        view.showMessageDialog(info.toString());
                    } else {
                        view.showErrorDialog("Room not found.");
                    }
                }
                break;

            case "Reservation Information":
                List<Reservation> reservations = hotel.getAllReservations();
                if (reservations.isEmpty()) {
                    view.showErrorDialog("No reservations found for this hotel.");
                    return;
                }
                String[] reservationLabels = reservations.stream()
                        .map(res -> res.getGuestName() + ": " + res.getCheckInDate() + " - " + res.getCheckOutDate() + ": " + res.getRoom().getName())
                        .toArray(String[]::new);
                String selectedReservationLabel = (String) JOptionPane.showInputDialog(null, "Select a reservation to view details:",
                        "Reservation Selection", JOptionPane.QUESTION_MESSAGE, null, reservationLabels, reservationLabels[0]);

                if (selectedReservationLabel != null) {
                    int selectedIndex = -1;
                    for (int i = 0; i < reservationLabels.length; i++) {
                        if (reservationLabels[i].equals(selectedReservationLabel)) {
                            selectedIndex = i;
                            break;
                        }
                    }

                    if (selectedIndex != -1) {
                        Reservation selectedReservation = reservations.get(selectedIndex);
                        StringBuilder info = new StringBuilder();
                        info.append("Guest Name: ").append(selectedReservation.getGuestName()).append("\n");
                        info.append("Room: ").append(selectedReservation.getRoom().getName()).append("\n");
                        info.append("Room Type: ").append(selectedReservation.getRoom().getRoomType()).append("\n");
                        info.append("Check-in Date: ").append(selectedReservation.getCheckInDate()).append("\n");
                        info.append("Check-out Date: ").append(selectedReservation.getCheckOutDate()).append("\n");
                        info.append("Total Price: ").append(selectedReservation.calculateTotalPrice()).append("\n");
                        info.append("\nPrice Breakdown:\n");
                        for (int day = selectedReservation.getCheckInDate(); day < selectedReservation.getCheckOutDate(); day++) {
                            info.append("Day ").append(day).append(": ").append(selectedReservation.getRoom().getBasePrice()).append("\n");
                        }
                        view.showMessageDialog(info.toString());
                    } else {
                        view.showErrorDialog("Reservation not found.");
                    }
                }
                break;


            case "Exit":
                view.dispose(); // Close the ManageHotelView
                MainMenuView mainMenuView = new MainMenuView();
                new MainMenuController(mainMenuView, hotelSystem);
                mainMenuView.setVisible(true);
                break;
        }
    }
}
