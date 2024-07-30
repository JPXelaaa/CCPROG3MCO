import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;
import java.util.*;

public class ManageHotelView extends JFrame {

    private JLabel overallRoomCountLabel;
    private JLabel standardRoomCountLabel;
    private JLabel deluxeRoomCountLabel;
    private JLabel executiveRoomCountLabel;

    private JButton addRoomButton;
    private JButton removeRoomButton;
    private JButton changeHotelNameButton;
    private JButton updateBasePriceButton;
    private JButton removeReservationButton;
    private JButton viewHotelInformationButton;
    private JButton viewReservationsButton;
    private JButton removeHotelButton;
    private JButton setDatePriceButton;
    private JButton backButton;
    private JButton addStandardRoomButton;
    private JButton addDeluxeRoomButton;
    private JButton addExecutiveRoomButton;


    public ManageHotelView(String hotelName) {
        super("Manage Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 700);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(20, 50, 20, 50));
        add(panel);

        placeComponents(panel);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {

        Dimension buttonSize = new Dimension(200, 40);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        JLabel titleLabel = new JLabel(">=====Manage Hotel=====<", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        changeHotelNameButton = new JButton("Change Hotel Name");
        changeHotelNameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeHotelNameButton.setMaximumSize(buttonSize);
        panel.add(changeHotelNameButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        addRoomButton = new JButton("Add Room");
        addRoomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addRoomButton.setMaximumSize(buttonSize);
        panel.add(addRoomButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        removeRoomButton = new JButton("Remove Room");
        removeRoomButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeRoomButton.setMaximumSize(buttonSize);
        panel.add(removeRoomButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        updateBasePriceButton = new JButton("Update Base Price");
        updateBasePriceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateBasePriceButton.setMaximumSize(buttonSize);
        panel.add(updateBasePriceButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        removeReservationButton = new JButton("Remove Reservation");
        removeReservationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeReservationButton.setMaximumSize(buttonSize);
        panel.add(removeReservationButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        viewHotelInformationButton = new JButton("View Hotel Information");
        viewHotelInformationButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewHotelInformationButton.setMaximumSize(buttonSize);
        panel.add(viewHotelInformationButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        viewReservationsButton = new JButton("View Reservations");
        viewReservationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewReservationsButton.setMaximumSize(buttonSize);
        panel.add(viewReservationsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        setDatePriceButton = new JButton("Set Date Price Modifier");
        setDatePriceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        setDatePriceButton.setMaximumSize(buttonSize);
        panel.add(setDatePriceButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        removeHotelButton = new JButton("Remove Hotel");
        removeHotelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeHotelButton.setMaximumSize(buttonSize);
        panel.add(removeHotelButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(buttonSize);
        panel.add(backButton);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showConfirmationDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public void setActionListener(ActionListener listener) {
        changeHotelNameButton.addActionListener(listener);
        addRoomButton.addActionListener(listener);
        removeRoomButton.addActionListener(listener);
        updateBasePriceButton.addActionListener(listener);
        removeReservationButton.addActionListener(listener);
        viewHotelInformationButton.addActionListener(listener);
        viewReservationsButton.addActionListener(listener);
        removeHotelButton.addActionListener(listener);
        setDatePriceButton.addActionListener(listener);
        backButton.addActionListener(listener);
    }

    public void showAddRoomPanel(int overallRoomCount, int standardRoomCount, int deluxeRoomCount, int executiveRoomCount, ActionListener listener) {
        JDialog dialog = new JDialog(this, "Add Room", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(5, 2));

        overallRoomCountLabel = new JLabel("Total Rooms: " + overallRoomCount);
        standardRoomCountLabel = new JLabel("Standard Rooms: " + standardRoomCount);
        deluxeRoomCountLabel = new JLabel("Deluxe Rooms: " + deluxeRoomCount);
        executiveRoomCountLabel = new JLabel("Executive Rooms: " + executiveRoomCount);

        addStandardRoomButton = new JButton("Add Standard");
        addStandardRoomButton.setActionCommand("Add Standard Room");
        addStandardRoomButton.addActionListener(listener);

        addDeluxeRoomButton = new JButton("Add Deluxe");
        addDeluxeRoomButton.setActionCommand("Add Deluxe Room");
        addDeluxeRoomButton.addActionListener(listener);

        addExecutiveRoomButton = new JButton("Add Executive");
        addExecutiveRoomButton.setActionCommand("Add Executive Room");
        addExecutiveRoomButton.addActionListener(listener);

        dialog.add(overallRoomCountLabel);
        dialog.add(new JLabel("")); // Empty cell for spacing
        dialog.add(standardRoomCountLabel);
        dialog.add(addStandardRoomButton);
        dialog.add(deluxeRoomCountLabel);
        dialog.add(addDeluxeRoomButton);
        dialog.add(executiveRoomCountLabel);
        dialog.add(addExecutiveRoomButton);

        dialog.setVisible(true);
    }

    public Room showRemoveRoomDialog(java.util.List<Room> availableRooms) {
        int selectedIndex;

        Collections.sort(availableRooms, new Comparator<Room>() {
            @Override
            public int compare(Room r1, Room r2) {
                int typeCompare = r1.getRoomType().compareTo(r2.getRoomType());
                if (typeCompare == 0) {
                    return r1.getName().compareTo(r2.getName());
                }
                return typeCompare;
            }
        });

        JPanel managePanel = new JPanel();
        managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));

        DefaultListModel<String> roomListModel = new DefaultListModel<>();
        for (Room room : availableRooms) {
            roomListModel.addElement(room.getName());
        }

        JList<String> roomList = new JList<>(roomListModel);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane roomListScrollPane = new JScrollPane(roomList);

        managePanel.add(roomListScrollPane);

        int result = JOptionPane.showConfirmDialog(this, managePanel, "Remove Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            selectedIndex = roomList.getSelectedIndex();
            if (selectedIndex != -1) {
                return availableRooms.get(selectedIndex);
            }
        }
        return null;
    }

    public double showUpdateBasePriceDialog() {
        String input = JOptionPane.showInputDialog(this, "Enter new base price (must be >= 100):", "Update Base Price", JOptionPane.PLAIN_MESSAGE);
        if (input != null) {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                showErrorDialog("Invalid input. Please enter a valid number.");
            }
        }
        return -1; // Indicate that the input was cancelled or invalid
    }

    public Room showRoomsWithReservationsDialog(java.util.List<Room> roomsWithReservations) {
        int result, selectedIndex;
        JPanel managePanel = new JPanel();
        managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));

        DefaultListModel<String> roomListModel = new DefaultListModel<>();
        for (Room room : roomsWithReservations) {
            roomListModel.addElement(room.getName());
        }

        JList<String> roomList = new JList<>(roomListModel);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane roomListScrollPane = new JScrollPane(roomList);

        managePanel.add(roomListScrollPane);

        result = JOptionPane.showConfirmDialog(this, managePanel, "Select Room with Reservations", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            selectedIndex = roomList.getSelectedIndex();
            if (selectedIndex != -1) {
                return roomsWithReservations.get(selectedIndex);
            }
        }
        return null;
    }

    public Reservation showReservationsDialog(java.util.List<Reservation> reservations) {
        JPanel managePanel = new JPanel();
        managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));

        DefaultListModel<String> reservationListModel = new DefaultListModel<>();
        for (Reservation reservation : reservations) {
            String reservationInfo = reservation.getGuestName() + " : " + reservation.getCheckInDate() + " - " + reservation.getCheckOutDate();
            reservationListModel.addElement(reservationInfo);
        }

        JList<String> reservationList = new JList<>(reservationListModel);
        reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane reservationListScrollPane = new JScrollPane(reservationList);

        managePanel.add(reservationListScrollPane);

        int result = JOptionPane.showConfirmDialog(this, managePanel, "Select Reservation to Remove", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int selectedIndex = reservationList.getSelectedIndex();
            if (selectedIndex >= 0) {
                return reservations.get(selectedIndex);
            }
        }
        return null;
    }

    public void showHotelInformation(Hotel hotel) {
        StringBuilder hotelInfo = new StringBuilder();
        hotelInfo.append("Hotel Name: ").append(hotel.getName()).append("\n");
        hotelInfo.append("Total Rooms: ").append(hotel.getRooms().size()).append("\n");
        hotelInfo.append("Estimated Earnings: ").append(hotel.calculateEarnings()).append("\n");

        hotelInfo.append("\nRooms: \n");

        hotelInfo.append("\nSTANDARD ROOMS:\n");
        for (Room room : hotel.getRooms()) {
            if (room.getRoomType() == RoomType.STANDARD) {
                hotelInfo.append(" - ").append(room.getName()).append(" | Base Price: ").append(room.calculatePrice(room.getBasePrice(), room.getRoomType())).append("\n");
            }
        }
        hotelInfo.append("\nDELUXE ROOMS:\n");
        for (Room room : hotel.getRooms()) {
            if (room.getRoomType() == RoomType.DELUXE) {
                hotelInfo.append(" - ").append(room.getName()).append(" | Base Price: ").append(room.calculatePrice(room.getBasePrice(), room.getRoomType())).append("\n");
            }
        }
        hotelInfo.append("\nEXECUTIVE ROOMS:\n");
        for (Room room : hotel.getRooms()) {
            if (room.getRoomType() == RoomType.EXECUTIVE) {
                hotelInfo.append(" - ").append(room.getName()).append(" | Base Price: ").append(room.calculatePrice(room.getBasePrice(), room.getRoomType())).append("\n");
            }
        }
        JTextArea textArea = new JTextArea(hotelInfo.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Hotel Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public Reservation showReservationsDialog(Room room) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        DefaultListModel<String> reservationListModel = new DefaultListModel<>();
        for (Reservation reservation : room.getReservations()) {
            reservationListModel.addElement(reservation.getGuestName() + " : " + reservation.getCheckInDate() + " - " + reservation.getCheckOutDate());
        }

        JList<String> reservationList = new JList<>(reservationListModel);
        reservationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane reservationListScrollPane = new JScrollPane(reservationList);

        panel.add(new JLabel("Select a reservation:"));
        panel.add(reservationListScrollPane);

        int result = JOptionPane.showConfirmDialog(this, panel, "Select Reservation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            int selectedIndex = reservationList.getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < room.getReservations().size()) {
                return room.getReservations().get(selectedIndex);
            }
        }
        return null;
    }

    public void showReservationDetails(Reservation reservation) {
        Room room;
        StringBuilder details = new StringBuilder();
        details.append("Guest Name: ").append(reservation.getGuestName()).append("\n");
        details.append("Room: ").append(reservation.getRoom().getName()).append("\n");
        details.append("Room Type: ").append(reservation.getRoom().getRoomType()).append("\n");
        details.append("Check-in Date: ").append(reservation.getCheckInDate()).append("\n");
        details.append("Check-out Date: ").append(reservation.getCheckOutDate()).append("\n");
        details.append("Total Price: ").append(reservation.calculateTotalPrice()).append("\n");

        details.append("\nPrice Breakdown:\n");
        room = reservation.getRoom();
        for (int day = reservation.getCheckInDate(); day < reservation.getCheckOutDate(); day++) {
            details.append("Day ").append(day).append(": ").append(room.getDailyPrice(day)).append("\n");
        }

        JTextArea textArea = new JTextArea(details.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Reservation Details", JOptionPane.INFORMATION_MESSAGE);
    }

    public int showDatePriceModifierDialog(double[] modifiers) {
        String dayStr;
        int dayStore;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        for (int i = 0; i < modifiers.length; i++) {
            panel.add(new JLabel(String.format("%02d : %.0f%%", i + 1, modifiers[i] * 100)));
        }

        dayStr = JOptionPane.showInputDialog(this, panel, "Select a day to modify", JOptionPane.PLAIN_MESSAGE);
        if (dayStr != null && !dayStr.trim().isEmpty()) {
            dayStore = Integer.parseInt(dayStr);
            if(dayStore >= 1 && dayStore <= 31){
                return dayStore;
            }
            else{
                showErrorDialog("Invalid Date Inputted.");
            }
        }
        return -1;
    }

    public double showNewPriceModifierDialog() {
        String modifierStr = JOptionPane.showInputDialog(this, "Enter the new price modifier (e.g., 1.1 for 110%, 0.9 for 90%):");
        if (modifierStr != null && !modifierStr.trim().isEmpty()) {
            return Double.parseDouble(modifierStr);
        }
        return -1;
    }

    public void goBackToMainMenu() {
        this.setVisible(false);
    }

    public void updateRoomCountLabels(int overallRoomCount, int standardRoomCount, int deluxeRoomCount, int executiveRoomCount) {
        overallRoomCountLabel.setText("Total Rooms: " + overallRoomCount);
        standardRoomCountLabel.setText("Standard Rooms: " + standardRoomCount);
        deluxeRoomCountLabel.setText("Deluxe Rooms: " + deluxeRoomCount);
        executiveRoomCountLabel.setText("Executive Rooms: " + executiveRoomCount);
    }
}