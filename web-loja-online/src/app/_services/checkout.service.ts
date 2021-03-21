import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { HttpHeaders } from '@angular/common/http';
import { KeycloakService } from 'keycloak-angular';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private http: HttpClient, private keycloakService: KeycloakService)  { }

  checkout(body, token){

    const container = 'jbpm_bpm_loja_online_1.0.0-SNAPSHOT';
    const process = 'com.myspace.jbpm_bpm_loja_online.compraOnline';

    const serviceURL = `${environment.apiBPMUrl}containers/` + container + `/processes/` + process + `/instances`;

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'Authorization': 'Bearer '+token,
        'Accept':'application/json'
      })
    };

    //'Authorization': 'Basic d2JhZG1pbjp3YmFkbWlu',

    return this.http.post<string[]>(serviceURL, body, httpOptions);
  }
}
