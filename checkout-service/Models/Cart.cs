using System.Collections.Generic;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace checkout_service.Models
{
    public class Cart{
         [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id { get; set; }
        public string User {get;set;}
        public CartStatus Status {get;set;}
        public List<CartItem> CartItemList {get;set;}
    }
}