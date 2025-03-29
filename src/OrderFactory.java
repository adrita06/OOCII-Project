public class OrderFactory {
    public static Order createOrder(OrderType type) {
        switch (type) {
            case CUSTOMIZE:
                return new CustomizeOrder();
            case PREDEFINED:
                return new PredefinedMenuOrder();
            default:
                throw new IllegalArgumentException("Invalid order type");
        }
    }
}
