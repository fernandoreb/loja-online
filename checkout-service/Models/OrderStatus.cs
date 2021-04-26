namespace checkout_service.Models
{
    public enum OrderStatus
    {
        Open,
        PendingPayment,
        Payed,
        AwatingDelivery,
        Delivered,
        Completed,
        Cancelled
    }
}