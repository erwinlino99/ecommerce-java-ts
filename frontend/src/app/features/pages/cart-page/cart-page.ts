import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../../service/api.service';
import { Observable, tap } from 'rxjs';
import { ShopCartItem } from '../../../shared/model-interface/ShopCartItem';
import { ShopCart } from '../../../shared/model-interface/ShopCart';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cart-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cart-page.html',
  styleUrl: './cart-page.scss',
})
export class CartPage implements OnInit {
  shopCart$!: Observable<ShopCart>;
  constructor(private api: ApiService) {}

  ngOnInit(): void {
    this.fetchWebUserCart();
  }

  fetchWebUserCart() {
    const endpoint = '/shop-cart/cart';
    console.log('VER CARRO ->');

    /**
     *
     * EN CASO DE QUE  HAGAMOS UN FETCH AL OBSERVABLE Y ESTO NO LO
     * UTILICEMOS EN NINGUN LUGAR COMO POR EJEMPLO EL HTML NO SE MOSTRARÁ
     * NI USANDO TAP CON EL CONSOLE.LOG
     *
     * SI SOLO QUEREMOS VER EL RESULTADO CON .SUSCRIBE DEBERIA DE VALE , PERO NO CON PIPE
     *
     */

    this.shopCart$ = this.api.get<ShopCart>(endpoint).pipe(
      tap((items) => {
        console.log('MI CARRO ->', items);
      })
    );

    // this.api.get<ShopCart>(endpoint).subscribe({
    //   next: (value) => {
    //     console.log('MI CARRO ->', value);
    //   },
    // });
  }
}
