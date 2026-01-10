import { Component, Input, input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShopProduct } from '../../../../shared/model-interface/ShopProduct';
import { ApiService } from '../../../../service/api.service';
import { SessionService } from '../../../../service/session.service';
import { error } from 'console';
import { PopupService } from '../../../../service/pop.up.data.service';
import { Route, Router } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
//METEMOS AL HIJO DENTRO DE IMPORTS
@Component({
  selector: 'shop-product-card',
  standalone: true,
  imports: [CommonModule,MatIcon],
  templateUrl: './shop-product-card.html',
  styleUrl: './shop-product-card.scss',
})
export class ShopProductCard {
  //INYECTAMOS LA INFORMACION DEL COMPONENTE PADRE DE "PRODUCTS-PAGE.TS"
  @Input({ required: true }) shopProduct!: ShopProduct;

  constructor(
    private api: ApiService,
    private session: SessionService,
    private popup: PopupService,
    private router: Router
  ) {}

  buyItem(shopProductId: number) {
    const endpoint = `shop-cart/add`;
    const body = {
      shopProductId: shopProductId,
      quantity: 1,
      action:true
    };

    this.api.post(endpoint, body).subscribe({
      next: (answer) => {
        this.popup.success('Producto agregado');
      },
      error: (error) => {
        console.error(error);
      },
    });
  }

  goDetailPage(shopProductId: number) {
    this.router.navigate(['/products/detail/', shopProductId]);
  }
}
