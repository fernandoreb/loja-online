import { environment } from './../../environments/environment';
import { Product } from './../_models/product';
import { ProductComments } from './../_models/productComments';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) {

  }

  getAll() {
    return this.http.get<Product[]>(`${environment.apiProductUrl}/product`);
  }

  getById(id) {
    return this.http.get<Product[]>(
      `${environment.apiProductUrl}/product` + '/' + id
    );
  }

  getCommentsBySku(sku) {
    return this.http.get<Product[]>(
      `${environment.apiProductCommentsUrl}/productsComments` + '/' + sku
    );
  }
}
