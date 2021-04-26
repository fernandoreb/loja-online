using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace checkout_service.Models
{
    public class OrderTrack
    {
        [BsonId]
        [BsonRepresentation(BsonType.ObjectId)]
        public string Id{get;set;}
        public string Date{get;set;}
        public string trackDescription{get;set;}        
    }

}