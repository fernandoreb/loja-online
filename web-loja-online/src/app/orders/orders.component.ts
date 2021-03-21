import { OrdersService } from './../_services/orders.service';
import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  constructor(private orderService:OrdersService, private keycloakService: KeycloakService) { }
  
  orders;
  displayedColumns: string[] = ['process-instance-id','process-instance-state','start-date'];
  
  async ngOnInit() {

    let token = await this.keycloakService.getToken();

    this.orderService.listOrders(token)
    .subscribe(resp =>{
      this.orders = resp["process-instance"];
      console.log(this.orders);
      this.updateStatus();
    });
  }

  /*
  ngOnInit() {
    this.orderService.listOrders()
    .then(resp =>{
      resolve();
      this.orders = resp["process-instance"];
      console.log(this.orders);
      this.updateStatus();
    });
  }
  */

  updateStatus(){
    for (let i = 0; i < this.orders.length; i++) {
      if(this.orders[i]['process-instance-state']==1)
        this.orders[i]['process-instance-state'] = "Processamento";

      if(this.orders[i]['process-instance-state']==2)
        this.orders[i]['process-instance-state'] = "Finalizado";        

      if(this.orders[i]['process-instance-state']==3)
        this.orders[i]['process-instance-state'] = "Não Aprovado";
      
      this.orders[i]['start-date'] = this.orders[i]['start-date']['java.util.Date'];
        
    }
  }

}
