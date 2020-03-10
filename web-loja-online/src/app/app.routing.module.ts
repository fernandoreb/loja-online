import { HomeLojaComponent } from './home-loja/home-loja.component';
import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';

import { ProductListComponent } from './product-list/product-list.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CartComponent } from './cart/cart.component';
import { ShippingComponent } from './shipping/shipping.component';
import { AuthGuard } from './_guards';

const appRoutes: Routes = [
  {
    path: '',
    component: HomeLojaComponent,
    canActivate: [AuthGuard],
    data: { roles: ['SuperUser', 'NormalUser'] }
  },
  {
    path: 'products',
    component: ProductListComponent,
    canActivate: [AuthGuard],
    data: { roles: ['SuperUser', 'NormalUser'] }
  },
  {
    path: 'products/:productId',
    component: ProductDetailsComponent,
    canActivate: [AuthGuard],
    data: { roles: ['SuperUser', 'NormalUser'] }
  },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [AuthGuard],
    data: { roles: ['SuperUser', 'NormalUser'] }
  },
  {
    path: 'shipping',
    component: ShippingComponent,
    canActivate: [AuthGuard],
    data: { roles: ['SuperUser', 'NormalUser'] }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule],
  providers: [AuthGuard]
})
export class AppRoutingModule {}
