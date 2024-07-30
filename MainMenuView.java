
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.stream.Collectors;

public class MainMenuView extends JFrame {
    private JButton createHotelButton;
    private JButton viewHotelButton;
    private JButton manageHotelButton;
    private JButton simulateBookingButton;
    private JButton listHotelsButton;
    private JButton removeHotelButton;
    private JButton exitButton;

    public MainMenuView() {
        super("Hotel Reservation System");
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

        Dimension buttonSize = new Dimension(200, 40);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        JLabel titleLabel = new JLabel(">==Hotel Reservation System==<", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Georgia", Font.BOLD, 28));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));

        createHotelButton = new JButton("Create Hotel");
        createHotelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createHotelButton.setMaximumSize(buttonSize);
        panel.add(createHotelButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        viewHotelButton = new JButton("View Hotel");
        viewHotelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewHotelButton.setMaximumSize(buttonSize);
        panel.add(viewHotelButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        manageHotelButton = new JButton("Manage Hotel");
        manageHotelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        manageHotelButton.setMaximumSize(buttonSize);
        panel.add(manageHotelButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        simulateBookingButton = new JButton("Simulate Booking");
        simulateBookingButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        simulateBookingButton.setMaximumSize(buttonSize);
        panel.add(simulateBookingButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        listHotelsButton = new JButton("List Hotels");
        listHotelsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        listHotelsButton.setMaximumSize(buttonSize);
        panel.add(listHotelsButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        removeHotelButton = new JButton("Remove Hotel");
        removeHotelButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        removeHotelButton.setMaximumSize(buttonSize);
        panel.add(removeHotelButton);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(buttonSize);
        panel.add(exitButton);
    }

    public void setActionListener(ActionListener listener) {
        createHotelButton.addActionListener(listener);
        removeHotelButton.addActionListener(listener);
        manageHotelButton.addActionListener(listener);
        listHotelsButton.addActionListener(listener);
        viewHotelButton.addActionListener(listener); // AD
        simulateBookingButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
    public String showManageHotelDialog(java.util.List<String> hotelNames) {
        JPanel managePanel = new JPanel();
        managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));

        DefaultListModel<String> hotelListModel = new DefaultListModel<>();
        for (String hotelName : hotelNames) {
            hotelListModel.addElement(hotelName);
        }

        JList<String> hotelList = new JList<>(hotelListModel);
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane hotelListScrollPane = new JScrollPane(hotelList);

        managePanel.add(hotelListScrollPane);

        int result = JOptionPane.showConfirmDialog(this, managePanel, "Manage Hotel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return hotelList.getSelectedValue();
        }
        return null;
    }

    public String showHotelDialog(java.util.List<String> hotelNames) {
        DefaultListModel<String> hotelListModel = new DefaultListModel<>();
        for (String hotelName : hotelNames) {
            hotelListModel.addElement(hotelName);
        }
        JList<String> hotelList = new JList<>(hotelListModel);
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane hotelListScrollPane = new JScrollPane(hotelList);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(hotelListScrollPane, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(this, panel, "Select Hotel", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return hotelList.getSelectedValue();
        }
        return null;
    }

    public String showSelectHotelDialog(java.util.List<String> hotelNames, String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        DefaultListModel<String> hotelListModel = new DefaultListModel<>();
        for (String hotelName : hotelNames) {
            hotelListModel.addElement(hotelName);
        }

        JList<String> hotelList = new JList<>(hotelListModel);
        hotelList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane hotelListScrollPane = new JScrollPane(hotelList);

        panel.add(hotelListScrollPane);

        int result = JOptionPane.showConfirmDialog(this, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return hotelList.getSelectedValue();
        }
        return null;
    }

    public String showSelectRoomDialog(java.util.List<Room> rooms) {
        DefaultListModel<String> roomListModel = new DefaultListModel<>();

        java.util.List<Room> standardRooms = rooms.stream()
                .filter(room -> room.getRoomType() == RoomType.STANDARD)
                .collect(Collectors.toList());

        java.util.List<Room> deluxeRooms = rooms.stream()
                .filter(room -> room.getRoomType() == RoomType.DELUXE)
                .collect(Collectors.toList());

        java.util.List<Room> executiveRooms = rooms.stream()
                .filter(room -> room.getRoomType() == RoomType.EXECUTIVE)
                .collect(Collectors.toList());

        for (Room room : standardRooms) {
            roomListModel.addElement(room.getName());
        }
        for (Room room : deluxeRooms) {
            roomListModel.addElement(room.getName());
        }
        for (Room room : executiveRooms) {
            roomListModel.addElement(room.getName());
        }

        JList<String> roomList = new JList<>(roomListModel);
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane roomListScrollPane = new JScrollPane(roomList);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(roomListScrollPane, BorderLayout.CENTER);

        int result = JOptionPane.showConfirmDialog(this, panel, "Select Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            return roomList.getSelectedValue();
        }
        return null;
    }

    public BookingDetails showBookingDetailsDialog() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2));

        JTextField guestNameField = new JTextField();
        JTextField checkInDateField = new JTextField();
        JTextField checkOutDateField = new JTextField();
        JTextField discountCodeField = new JTextField();

        panel.add(new JLabel("Guest Name:"));
        panel.add(guestNameField);
        panel.add(new JLabel("Check-In Date (1-30):"));
        panel.add(checkInDateField);
        panel.add(new JLabel("Check-Out Date (2-31):"));
        panel.add(checkOutDateField);
        panel.add(new JLabel("Discount Code:"));
        panel.add(discountCodeField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Enter Booking Details", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String guestName = guestNameField.getText();
                int checkInDate = Integer.parseInt(checkInDateField.getText());
                int checkOutDate = Integer.parseInt(checkOutDateField.getText());
                String discountCode = discountCodeField.getText();

                if (checkInDate < 1 || checkInDate > 30 || checkOutDate < 2 || checkOutDate > 31 || checkInDate > checkOutDate) {
                    showErrorDialog("Invalid dates entered.");
                    return null;
                }

                return new BookingDetails(guestName, checkInDate, checkOutDate, discountCode);
            } catch (NumberFormatException ex) {
                showErrorDialog("Invalid input. Please enter valid dates.");
            }
        }
        return null;
    }

    public String showInfoLevelDialog() {
        String[] options = {"High-Level Information", "Low-Level Information"};
        int result = JOptionPane.showOptionDialog(this, "Select information level:", "Information Level",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (result >= 0) {
            return options[result];
        }
        return null;
    }

    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
