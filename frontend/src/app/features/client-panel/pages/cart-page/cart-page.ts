import { Component } from '@angular/core';
import { ApiService } from '../../../../service/api.service';
import { BehaviorSubject, Observable, switchMap, tap } from 'rxjs'; // Importamos switchMap y BehaviorSubject
import { ShopCart } from '../../../../shared/model-interface/ShopCart';
import { CommonModule } from '@angular/common';
import { PopupService } from '../../../../service/pop.up.data.service';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.scss',
})
export class CartPage {
  private refreshCart$ = new BehaviorSubject<void>(undefined);

  shopCart$: Observable<ShopCart> = this.refreshCart$.pipe(
    switchMap(() => this.api.get<ShopCart>('/shop-cart/cart'))
  );

  constructor(private api: ApiService, private popup: PopupService) {}

  private getBaseBody(productId: number) {
    return { shopProductId: productId, quantity: 1 };
  }

  fetchWebUserCart() {
    this.refreshCart$.next();
  }

  decreaseQuantity(shopProductId: number) {
    const endpoint = '/shop-cart/decrease';
    const body = { ...this.getBaseBody(shopProductId), action: false };
    this.api.post(endpoint, body).subscribe({
      next: () => {
        this.popup.error('Producto reducido');
        this.fetchWebUserCart();
      },
    });
  }

  increaseQuantity(shopProductId: number) {
    const endpoint = '/shop-cart/add';
    const body = { ...this.getBaseBody(shopProductId), action: true };
    this.api.post(endpoint, body).subscribe({
      next: () => {
        this.popup.success('Producto agregado');
        this.fetchWebUserCart();
      },
    });
  }

  emptyShopCartItem(shopProductId: number) {
    const endpoint = '/shop-cart/empty-shop-cart-item';
    this.api.post(endpoint, this.getBaseBody(shopProductId)).subscribe({
      next: () => {
        this.popup.error('PRODUCTO ELIMINADO');
        this.fetchWebUserCart();
      },
    });
  }

  purcharseCart() {
    const endpoint = '/shop-cart/purchase';
    this.api.post(endpoint).subscribe({
      next: () => {
        this.popup.success('CARRITO COMPRADO');
        this.fetchWebUserCart();
      },
      error: (err) => {
        const products = err.error?.details;
        if (Array.isArray(products) && products.length > 0) {
          console.log(products);
        }
      },
    });
  }

  emptyCurrentCart(): void {
    const endpoint = '/shop-cart/empty';
    this.api.post(endpoint).subscribe({
      next: (data) => {
        this.popup.success('EL CARRO HA SIDO VACIADO');
        this.fetchWebUserCart();
      },
    });
  }
}
