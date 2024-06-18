public class TenDigitNumber {
    private final String number;

    public TenDigitNumber(String number) {
        if (number == null) {
            throw new IllegalArgumentException("Number cannot be null.");
        }
        if (!number.matches("\\d{10}")) {
            throw new IllegalArgumentException("Number must be exactly 10 digits long and contain only digits from 0 to 9.");
        }
        this.number = number;
    }

    @Override
    public String toString() {
        return number;
    }

    public String getNumber() {
        return number;
    }

    public static void main(String[] args) {}
}
