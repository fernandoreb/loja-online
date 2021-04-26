using checkout_service.Models;
using checkout_service.Services;
using Microsoft.AspNetCore.Mvc;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Text.Json;

namespace checkout_service.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class OrdersController:ControllerBase
    {
        private readonly OrderService _OrderService;

        public OrdersController(OrderService OrderService)
        {
            _OrderService = OrderService;
        }

        [HttpGet]
        public ActionResult<List<Order>> Get() =>
            _OrderService.GetOrders();
        
        [HttpGet("{id:length(24)}", Name = "GetOrder")]
        public ActionResult<Order>Get(string id){
            var Order = _OrderService.GetOrder(id);
            if(Order == null)
                return NotFound();
            return Order;
        }

        [HttpPost]
        public async Task<ActionResult<Order>> Create([FromBody] JsonElement body)
        {
            string cartId = body.GetProperty("cartId").GetString();
            
            Order Order = await _OrderService.Create(cartId);

            if(Order == null)
            {
                return NotFound();
            }

            return CreatedAtRoute("GetOrder",new {id = Order.Id.ToString()},Order);
        }

        [HttpPut("{id:length(24)}")]
        public IActionResult Update(string id, Order OrderIn)
        {
            var Order = _OrderService.GetOrder(id);
            if(Order == null)
            {
                return NotFound();
            }

            _OrderService.Update(id,OrderIn);

            return NoContent();
        }

        [HttpDelete("{id:length(24)}")]
        public IActionResult Delete(string id)
        {
            var Order = _OrderService.GetOrder(id);
            if(Order == null)
            {
                return NotFound();
            }
            _OrderService.Remove(Order.Id);

            return NoContent();
        }
    }
}