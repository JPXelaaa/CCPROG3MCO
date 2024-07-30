import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewHotelView extends JFrame {
    private JButton availableBookedRoomsButton;
    private JButton roomInformationButton;
    private JButton reservationInformationButton;
    private JButton exitButton;

    public ViewHotelView(String hotelName) {
        super("View Hotel: " + hotelName);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        add(panel);

        placeComponents(panel);

        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        Dimension buttonSize = new Dimension(300, 40);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));

        JLabel titleLabel = new JLabel(">==View Hotel==<", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        availableBookedRoomsButton = new JButton("Total Available and Booked Rooms");
        availableBookedRoomsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        availableBookedRoomsButton.setMaximumSize(buttonSize);
        panel.add(availableBookedRoomsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        roomInformationButton = new JButton("Room Information");
        roomInformationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomInformationButton.setMaximumSize(buttonSize);
        panel.add(roomInformationButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        reservationInformationButton = new JButton("Reservation Information");
        reservationInformationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reservationInformationButton.setMaximumSize(buttonSize);
        panel.add(reservationInformationButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(buttonSize);
        panel.add(exitButton);
    }

    public void setActionListener(ActionListener listener) {
        availableBookedRoomsButton.addActionListener(listener);
        roomInformationButton.addActionListener(listener);
        reservationInformationButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void displayRoomInformation(Hotel hotel) {
        String roomName = JOptionPane.showInputDialog("Enter room name to view details:");
        Room room = hotel.findRoom(roomName);

        if (room == null) {
            showErrorDialog("Room not found.");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("Room Name: ").append(room.getName()).append("\n");
        info.append("Base Price: ").append(room.getBasePrice()).append("\n");
        info.append("Room Type: ").append(room.getRoomType()).append("\n");
        info.append("\nAvailability:\n");
        boolean[] availability = room.getAvailability();
        for (int day = 1; day <= availability.length; day++) {
            info.append("Day ").append(day).append(": ").append(availability[day - 1] ? "Available" : "Booked").append("\n");
        }
        showMessageDialog(info.toString());
    }

    public void displayReservationInformation(Hotel hotel) {
        String roomName = JOptionPane.showInputDialog("Enter room name to view reservations:");
        Room room = hotel.findRoom(roomName);

        if (room == null) {
            showErrorDialog("Room not found.");
            return;
        }

        List<Reservation> reservations = room.getReservations();
        if (reservations.isEmpty()) {
            showErrorDialog("No reservations found for this room.");
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("Reservations for Room: ").append(room.getName()).append("\n");
        int index = 1;
        for (Reservation reservation : reservations) {
            info.append(index++).append(". Guest: ").append(reservation.getGuestName())
                    .append(", Check-in: ").append(reservation.getCheckInDate())
                    .append(", Check-out: ").append(reservation.getCheckOutDate())
                    .append(", Total Price: ").append(reservation.calculateTotalPrice()).append("\n");
        }

        String reservationIndexStr = JOptionPane.showInputDialog("Select reservation to view details (1-" + reservations.size() + "):");
        try {
            int reservationIndex = Integer.parseInt(reservationIndexStr) - 1;
            if (reservationIndex < 0 || reservationIndex >= reservations.size()) {
                showErrorDialog("Invalid reservation selection.");
                return;
            }

            Reservation selectedReservation = reservations.get(reservationIndex);
            info.setLength(0); // Clear the previous information
            info.append("Reservation Details:\n");
            info.append("Guest Name: ").append(selectedReservation.getGuestName()).append("\n");
            info.append("Room: ").append(selectedReservation.getRoom().getName()).append("\n");
            info.append("Room Type: ").append(selectedReservation.getRoom().getRoomType()).append("\n");
            info.append("Check-in Date: ").append(selectedReservation.getCheckInDate()).append("\n");
            info.append("Check-out Date: ").append(selectedReservation.getCheckOutDate()).append("\n");
            info.append("Total Price: ").append(selectedReservation.calculateTotalPrice()).append("\n");
            info.append("\nPrice Breakdown:\n");
            for (int day = selectedReservation.getCheckInDate(); day < selectedReservation.getCheckOutDate(); day++) {
                info.append("Day ").append(day).append(": ").append(room.getDailyPrice(1)).append("\n");
            }
            showMessageDialog(info.toString());
        } catch (NumberFormatException ex) {
            showErrorDialog("Invalid input. Please enter a valid number.");
        }
    }
}
