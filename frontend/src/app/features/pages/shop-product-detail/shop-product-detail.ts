import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable, of, catchError, tap } from 'rxjs';
import { ApiService } from '../../../service/api.service';
import { ShopProduct } from '../../../shared/model-interface/ShopProduct';
import { SessionService } from '../../../service/session.service';

@Component({
  selector: 'app-shop-product-detail',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './shop-product-detail.html',
  styleUrls: ['./shop-product-detail.scss'],
})
export class ShopProductDetail implements OnInit {
  shopProduct!: Observable<ShopProduct | null>;

  constructor(private api: ApiService, private route: ActivatedRoute, private session:SessionService) {

  }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('shopProductId'));
    const endpoint = `shop-product-id=${id}`;
    console.log('endpoint', endpoint);

    this.shopProduct = this.api.get<ShopProduct>(endpoint).pipe(
      tap((product) => {
        console.log('Producto recibido:', product);
        if (product) {
          console.log('ID:', product.id);
          console.log('Name:', product.name);
        }
      }),
      catchError((err) => {
        console.error(err);
        return of(null);
      })
    );
  }

  buyItem(shopProductId: number) {
    const quantity = 1;
    const webUserId = this.session.getUserId();
    const endpoint = `/shop-cart/web-user-id=${webUserId}/shop-product-id=${shopProductId}/quantity=${quantity}`;
    this.api.post(endpoint).subscribe({
      next: (answer) => {
        console.log('agregado', answer);
      },
      error: (error) => {
        console.error(error);
      },
    });
  }
}
