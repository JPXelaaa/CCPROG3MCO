// MainMenuController.java
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController implements ActionListener {
    private MainMenuView view;
    private HotelSystem hotelSystem;

    public MainMenuController(MainMenuView view, HotelSystem hotelSystem) {
        this.view = view;
        this.hotelSystem = hotelSystem;

        view.setActionListener(this);
        this.view.addViewHotelListener(new ViewHotelListener());
        this.view.addManageHotelListener(new ManageHotelListener());
        this.view.addSimulateBookingListener(new SimulateBookingListener());
        this.view.addListHotelsListener(new ListHotelsListener());
        this.view.addRemoveHotelListener(new RemoveHotelListener());
        this.view.addExitListener(new ExitListener());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String hotelName;
        switch(e.getActionCommand()){
        //Create Hotel Controller
        case "Create Hotel":
            hotelName = JOptionPane.showInputDialog("Enter hotel name:");

            if (hotelName != null && !hotelName.trim().isEmpty()) {
                if (hotelSystem.createHotel(hotelName)) {
                    JOptionPane.showMessageDialog(null, "Hotel created successfully.\nA default STANDARD room named 'S01' has been added to the hotel.");
                } else {
                    JOptionPane.showMessageDialog(null, "Hotel name must be unique. Failed to create hotel.");
                }
            }
            else if (hotelName != null){
                view.showErrorDialog("Please fill out the field.");
                break;
            }
            break;
            //Remove Hotel Controller
        case "Remove Hotel":
            hotelName = JOptionPane.showInputDialog("Enter hotel name to remove:");
            if (hotelSystem.removeHotel(hotelName)) {
                JOptionPane.showMessageDialog(null, "Hotel removed successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Hotel not found. Failed to remove hotel.");
            }
            break;
        }
    }


    class ViewHotelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Add code to handle viewing a hotel
        }
    }

    class ManageHotelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Add code to handle managing a hotel
        }
    }

    class SimulateBookingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Add code to handle simulating a booking
        }
    }

    class ListHotelsListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            hotelSystem.listHotels();
        }
    }

    class RemoveHotelListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ExitListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
