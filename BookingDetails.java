public class BookingDetails {
    private String guestName;
    private int checkInDate;
    private int checkOutDate;
    private String discountCode;

    public BookingDetails(String guestName, int checkInDate, int checkOutDate, String discountCode) {
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.discountCode = discountCode;
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

    public String getDiscountCode() {
        return discountCode;
    }
}
