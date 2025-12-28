import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { catchError, Observable, of, tap } from 'rxjs';
import { ShopOrder } from '../../../../shared/model-interface/ShopOrder';
import { ApiService } from '../../../../service/api.service';
import { PopupService } from '../../../../service/pop.up.data.service';
@Component({
  selector: 'app-shop-order-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './shop-order-page.html',
  styleUrl: './shop-order-page.scss',
})
export class ShopOrderPage implements OnInit {
  shopOrder$!: Observable<ShopOrder[]>;
  constructor(private api: ApiService, private popup: PopupService) {}

  ngOnInit(): void {
    this.fetchShopOrders();
  }
  fetchShopOrders() {
    const endpoint = '/shop-order/summary';
    this.shopOrder$ = this.api.get<ShopOrder[]>(endpoint).pipe(
      tap((data) => {
        // console.log('cantidad de pedidos', data.length);
        console.log('VER ESTO DIDI ', data);
      }),
      catchError((error) => {
        console.error('Error al cargar los pedidos:', error);
        return of([]);
      })
    );
  }
  repeatShopOrder(shopOrderId: number) {
    const endpoint = '/shop-order/repeat-shop-order';
    console.log('VAMOS A REPETIR ', shopOrderId);
    const body = {
      shopOrderId: shopOrderId,
    };
    this.api.post(endpoint, body).subscribe({
      next: (data) => {
        this.popup.success('PEDIDO REPETIDO EN EL CARRO', 2000);
        console.log(data)
      },
    });
  }
}
