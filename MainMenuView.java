
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuView extends JFrame{
    private JFrame frame;
    private JButton createHotelButton;
    private JButton viewHotelButton;
    private JButton manageHotelButton;
    private JButton simulateBookingButton;
    private JButton listHotelsButton;
    private JButton removeHotelButton;
    private JButton exitButton;

    public MainMenuView() {
        frame = new JFrame("Hotel Reservation System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);

        placeComponents(panel);

        frame.setVisible(true);
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


    public void setActionListener(ActionListener listener){
        createHotelButton.addActionListener(listener);
        removeHotelButton.addActionListener(listener);
    }

    public void addViewHotelListener(ActionListener listener) {
        viewHotelButton.addActionListener(listener);
    }

    public void addManageHotelListener(ActionListener listener) {
        manageHotelButton.addActionListener(listener);
    }

    public void addSimulateBookingListener(ActionListener listener) {
        simulateBookingButton.addActionListener(listener);
    }

    public void addListHotelsListener(ActionListener listener) {
        listHotelsButton.addActionListener(listener);
    }

    public void addRemoveHotelListener(ActionListener listener) {

    }

    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    public void showConfirmationDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }
}
