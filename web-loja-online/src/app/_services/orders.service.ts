import { Order } from './../_models/order';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {

  constructor(private http: HttpClient) { }

  listOrders(){

    const container = 'jbpm_bpm_loja_online_1.0.0-SNAPSHOT';
//    const process = 'com.myspace.jbpm_bpm_loja_online.compraOnline';

    const serviceURL = `${environment.apiBPMUrl}containers/` + container + `/processes/instances?status=1&status=2&status=3&pageSize=20`;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Basic d2JhZG1pbjp3YmFkbWlu',
        'Accept':'application/json'
      })
    };

    return this.http.get<any[]>(serviceURL, httpOptions);
  }
}
