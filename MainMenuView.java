
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

    /* MainMenuView Constructor
                a. Purpose: Initializes the MainMenuView, setting up the UI components and layout.
                b. Parameters: None
                c. Return type: None
*/
    public MainMenuView() {
        super("Hotel Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topBar = createBlackBar();
        JPanel bottomBar = createBlackBar();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.decode("#FFFDD0"));

        mainPanel.add(topBar, BorderLayout.NORTH);
        mainPanel.add(bottomBar, BorderLayout.SOUTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        placeComponents(centerPanel);

        setVisible(true);
    }

    /* placeComponents Method
                a. Purpose: Adds and arranges buttons and labels on the provided panel.
                b. Parameters:
                         - panel: JPanel
                c. Return type: void
*/

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
    /* setActionListener Method
                    a. Purpose: Sets the provided ActionListener for all buttons in the view.
                    b. Parameters:
                             - listener: ActionListener
                    c. Return type: void
    */
    public void setActionListener(ActionListener listener) {
        createHotelButton.addActionListener(listener);
        removeHotelButton.addActionListener(listener);
        manageHotelButton.addActionListener(listener);
        listHotelsButton.addActionListener(listener);
        viewHotelButton.addActionListener(listener); // AD
        simulateBookingButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
    /* createBlackBar Method
                    a. Purpose: Creates a JPanel with a colored background to be used as a bar.
                    b. Parameters: None
                    c. Return type: JPanel
    */
    private JPanel createBlackBar() {
        JPanel bar = new JPanel();
        bar.setBackground(Color.decode("#B7A684"));
        bar.setPreferredSize(new Dimension(800, 25));  // Fixed height for the bars
        return bar;
    }

    /* showManageHotelDialog Method
                    a. Purpose: Displays a dialog to select a hotel for management from a list of hotel names.
                    b. Parameters:
                             - hotelNames: java.util.List<String>
                    c. Return type: String
    */
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

    /* showHotelDialog Method
                    a. Purpose: Displays a dialog to select a hotel from a list of hotel names.
                    b. Parameters:
                             - hotelNames: java.util.List<String>
                    c. Return type: String
    */
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
    /* showSelectHotelDialog Method
                    a. Purpose: Displays a dialog to select a hotel from a list of hotel names with a custom title.
                    b. Parameters:
                             - hotelNames: java.util.List<String>
                             - title: String
                    c. Return type: String
    */
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
    /* showSelectRoomDialog Method
                    a. Purpose: Displays a dialog to select a room from a list of rooms.
                    b. Parameters:
                             - rooms: java.util.List<Room>
                    c. Return type: String
    */
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
    /* showBookingDetailsDialog Method
                    a. Purpose: Displays a dialog to enter booking details and returns a BookingDetails object.
                    b. Parameters: None
                    c. Return type: BookingDetails
    */
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
    /* showInfoLevelDialog Method
                    a. Purpose: Displays a dialog to select the information level (high-level or low-level).
                    b. Parameters: None
                    c. Return type: String
    */
    public String showInfoLevelDialog() {
        String[] options = {"High-Level Information", "Low-Level Information"};
        int result = JOptionPane.showOptionDialog(this, "Select information level:", "Information Level",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (result >= 0) {
            return options[result];
        }
        return null;
    }
    /* showMessageDialog Method
                    a. Purpose: Displays an informational message dialog.
                    b. Parameters:
                             - message: String
                    c. Return type: void
    */
    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    /* showErrorDialog Method
                    a. Purpose: Displays an error message dialog.
                    b. Parameters:
                             - message: String
                    c. Return type: void
    */
    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
