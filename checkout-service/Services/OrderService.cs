using checkout_service.Models;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Threading.Tasks;
using System.Text.Json;
using System;

namespace checkout_service.Services
{
    public class OrderService
    {
        private readonly IMongoCollection<Order> _Orders;
        private readonly IBackendServiceSettings _BackendSettings;
        public OrderService(IOrderDatabaseSettings DbSettings, IBackendServiceSettings BackendSettings)
        {
            var client = new MongoClient(DbSettings.ConnectionString);
            var database = client.GetDatabase(DbSettings.DatabaseName);

            _Orders = database.GetCollection<Order>(DbSettings.OrdersCollectionName);
            _BackendSettings = BackendSettings;
        }
        public List<Order>GetOrders()=>
        _Orders.Find(Order => true).ToList();
        public Order GetOrder(string id)=>
        _Orders.Find(Order => Order.Id == id).FirstOrDefault();
        public async Task<Order> Create(string cartId)
        {
            bool isExists = await IsExistsCart(cartId);

            if(!isExists)
                return null;

            Order Order = new Order();

            Order.CartId = cartId;
            Order.Status = OrderStatus.Open;
            
            _Orders.InsertOne(Order);
            return Order;
        }
        public void Update(string id, Order OrderIn) =>
            _Orders.ReplaceOne(Order => Order.Id == id, OrderIn);

        public void Remove(Order OrderIn) =>
            _Orders.DeleteOne(Order => Order.Id == OrderIn.Id);

        public void Remove(string id) => 
            _Orders.DeleteOne(Order => Order.Id == id);


        async private Task<bool> IsExistsCart(string cartId)
        {
            Console.WriteLine("fernandoreb:"+cartId);

            HttpClientHandler clientHandler = new HttpClientHandler();
            clientHandler.ServerCertificateCustomValidationCallback = (sender, cert, chain, sslPolicyErrors) => { return true; };
            using(var client = new HttpClient(clientHandler))
            {
                client.DefaultRequestHeaders.Accept.Clear();
                client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

                var response = await client.GetAsync(_BackendSettings.CartUrl+cartId);
                if (response.IsSuccessStatusCode)
                {
                    var msg = await response.Content.ReadAsStringAsync();
                    if(msg != null)
                    {
                        JsonDocument doc = JsonDocument.Parse(msg);
                        JsonElement cart = doc.RootElement.Clone();

                        if(cart.GetProperty("id").GetString() == cartId)
                        {
                            return true;
                        }
                    }
                }
                else if(response.StatusCode == System.Net.HttpStatusCode.NotFound){
                    return false;
                }
                throw new Exception("Generic Error");
                
            }
        }

    }
}