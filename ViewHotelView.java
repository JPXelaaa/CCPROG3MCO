import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewHotelView extends JFrame {
    private JButton availableBookedRoomsButton;
    private JButton roomInformationButton;
    private JButton reservationInformationButton;
    private JButton exitButton;

    /* ViewHotelView Constructor
        a. Purpose: Initializes the ViewHotelView with the specified hotel name, sets up the JFrame,
                    and places the components on the panel.
        b. Parameters:
                 - String hotelName: The name of the hotel to be displayed in the view.
        c. Return type: Constructor (void)
    */
    public ViewHotelView(String hotelName) {
        super("View Hotel: " + hotelName);
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
        a. Purpose: Adds and arranges the buttons and labels on the specified panel.
        b. Parameters:
                 - JPanel panel: The panel on which components will be placed.
        c. Return type: void
    */
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

    /* createBlackBar Method
        a. Purpose: Creates a JPanel with a fixed height and a background color for use as a top or bottom bar.
        b. Parameters: None
        c. Return type: JPanel
    */
    private JPanel createBlackBar() {
        JPanel bar = new JPanel();
        bar.setBackground(Color.decode("#B7A684"));
        bar.setPreferredSize(new Dimension(800, 25));  // Fixed height for the bars
        return bar;
    }
    /* setActionListener Method
        a. Purpose: Sets the action listener for the buttons in the view.
        b. Parameters:
                 - ActionListener listener: The action listener to be set for the buttons.
        c. Return type: void
    */
    public void setActionListener(ActionListener listener) {
        availableBookedRoomsButton.addActionListener(listener);
        roomInformationButton.addActionListener(listener);
        reservationInformationButton.addActionListener(listener);
        exitButton.addActionListener(listener);
    }
    /* showMessageDialog Method
         a. Purpose: Displays an information message dialog with the specified message.
         b. Parameters:
                  - String message: The message to be displayed in the dialog.
         c. Return type: void
     */
    public void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    /* showErrorDialog Method
        a. Purpose: Displays an error message dialog with the specified message.
        b. Parameters:
                 - String message: The message to be displayed in the dialog.
        c. Return type: void
    */
    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
