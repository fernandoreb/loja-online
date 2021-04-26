namespace checkout_service.Models
{
    public class CartDatabaseSettings : ICartDatabaseSettings
    {
        public string CartsCollectionName { get; set; }
        public string ConnectionString { get; set; }
        public string DatabaseName { get; set; }
    }

    public interface ICartDatabaseSettings
    {
        string CartsCollectionName { get; set; }
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
    }
}