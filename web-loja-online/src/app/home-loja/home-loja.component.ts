import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home-loja',
  templateUrl: './home-loja.component.html',
  styleUrls: ['./home-loja.component.css']
})
export class HomeLojaComponent implements OnInit {
  labelProducts;
  constructor() {
    this.labelProducts = 'Lista de Produtos';
  }

  ngOnInit() {
  }

}
