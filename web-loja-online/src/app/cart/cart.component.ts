import { Component, OnInit } from "@angular/core";
import { FormBuilder } from "@angular/forms";

import { CartService } from "../_services/cart.service";
import { CheckoutService } from "../_services/checkout.service";

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.css"]
})
export class CartComponent implements OnInit {
  items;
  checkoutForm;
  labelVoltar = "Voltar";
  numPedido;

  constructor(
    private cartService: CartService,
    private formBuilder: FormBuilder,
    private checkoutService: CheckoutService
  ) {}

  ngOnInit() {
    this.items = this.cartService.getItems();
    this.checkoutForm = this.formBuilder.group({
      name: '',
      address: ''
    });
  }

  onSubmit(customerData) {
    // Process checkout data here

    let body = '{"checkout":{"Checkout":{';
    body += '"address":"' + customerData.address + '",';
    body += '"receiptName":"' + customerData.name + ' ",';
    body += '"productList":[';

    const cart = this.cartService.getItems();

    cart.forEach( function (value) {
      body +=
        '{"sku":"' +
        value.sku +
        '", "name":"' +
        value.name +
        '","description":"' +
        value.description +
        '", "price":"' +
        value.price +
        '"},';
    });
    body = body.substr(0, body.length - 1);
    body += ']';
    body += '}}}';

    this.checkoutService.checkout(body).subscribe(resp => {
      this.numPedido = resp;
    });

    this.items = this.cartService.clearCart();
    this.checkoutForm.reset();
  }
}
