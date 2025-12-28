import { Component, OnInit } from '@angular/core';
import { ShopProduct } from '../../../../shared/model-interface/ShopProduct';
import { SessionService } from '../../../../service/session.service';
import { ApiService } from '../../../../service/api.service';
import { Route, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ShopProductCard } from '../../components/shop-product-card/shop-product-card';
import { catchError, Observable, of, tap } from 'rxjs';

@Component({
  selector: 'app-products-page',
  standalone: true,
  imports: [CommonModule, ShopProductCard],
  templateUrl: './products-page.html',
  styleUrl: './products-page.scss',
})
export class ProductsPage implements OnInit {
  shopProduct$!: Observable<ShopProduct[]>;

  constructor(private session: SessionService, private api: ApiService) {}
  ngOnInit() {
    this.getShopProducts();
  }

  private getShopProducts() {
    const endoint = '/all-shop-products';
    this.shopProduct$ = this.api.get<ShopProduct[]>(endoint).pipe(
      tap((items) => {
        // console.log('Products BACKEND', items);
      }),
      catchError((error) => {
        console.log('ERROR', error);
        return of([] as ShopProduct[]);
      })
    );

  }
}
