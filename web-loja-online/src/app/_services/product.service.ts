import { environment } from './../../environments/environment';
import { Product } from './../_models/product';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {

  }

  getAll() {
    return this.http.get<Product[]>(`${environment.apiProductUrl}/products`);
  }

  getById(id) {
    return this.http.get<Product[]>(
      `${environment.apiProductUrl}/products` + '/' + id
    );
  }
}
