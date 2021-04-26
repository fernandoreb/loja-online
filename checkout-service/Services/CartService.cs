using checkout_service.Models;
using MongoDB.Driver;
using System.Collections.Generic;
using System.Linq;

namespace checkout_service.Services
{
    public class CartService
    {
        private readonly IMongoCollection<Cart> _carts;

        public CartService(ICartDatabaseSettings settings)
        {
            var client = new MongoClient(settings.ConnectionString);
            var database = client.GetDatabase(settings.DatabaseName);

            _carts = database.GetCollection<Cart>(settings.CartsCollectionName);
        }
        public List<Cart>GetCarts()=>
        _carts.Find(cart => true).ToList();
        public Cart GetCart(string id)=>
        _carts.Find(cart => cart.Id == id).FirstOrDefault();
        public Cart Create()
        {
            Cart cart = new Cart();
            cart.Status = CartStatus.Anonymous;
            
            _carts.InsertOne(cart);
            return cart;
        }
        public void Update(string id, Cart cartIn) =>
            _carts.ReplaceOne(cart => cart.Id == id, cartIn);

        public void Remove(Cart cartIn) =>
            _carts.DeleteOne(cart => cart.Id == cartIn.Id);

        public void Remove(string id) => 
            _carts.DeleteOne(cart => cart.Id == id);

    }
}