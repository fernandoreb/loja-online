using System.Collections.Generic;
using MongoDB.Bson;
using MongoDB.Bson.Serialization.Attributes;

namespace checkout_service.Models
{
    public class Order
    {
          [BsonId]
          [BsonRepresentation(BsonType.ObjectId)]
          public string Id { get; set; }
          public OrderStatus Status { get; set;}
          public string CartId{get;set;}
          public OrderPaymentOptions PaymentOption { get; set; }
          public string TrackOrderid {get;set;}
          public string processId{get;set;}
    }
}