
public class Reservation {
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private Room room;
    private RoomType roomType;
    private double totalPrice;
    private String discountCode;

    public Reservation(String guestName, int checkInDate, int checkOutDate, Room room, String discountCode) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.room = room;
        this.discountCode = discountCode;
        this.totalPrice = calculateTotalPrice();
        room.getReservations().add(this);
    }
    public String getGuestName() {
        return guestName;
    }

    public int getCheckInDate() {
        return checkInDate;
    }

    public int getCheckOutDate() {
        return checkOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public double calculateTotalPrice() {
        double totalPrice = 0.0;
        for (int day = checkInDate; day < checkOutDate; day++) {
            totalPrice += room.getDailyPrice(day);
        }
        if (discountCode.equals("I_WORK_HERE")) {
            totalPrice *= 0.9; // 10% discount
        } else if (discountCode.equals("STAY4_GET1") && (checkOutDate - checkInDate) >= 5) {
            totalPrice -= room.calculatePrice(room.getBasePrice(), room.getRoomType()); // 1 day free
        } else if (discountCode.equals("PAYDAY") && (coversDay(15) || coversDay(30))) {
            totalPrice *= 0.93; // 7% discount
        } else if (discountCode.equals("NONE")){
        } else{
            System.out.println("Invalid Discount Code!");
        }
        return totalPrice;
    }

    private boolean coversDay(int day) {
        return checkInDate <= day && day < checkOutDate;
    }
}
