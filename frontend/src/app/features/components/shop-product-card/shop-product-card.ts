import { Component, Input, input, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShopProduct } from '../../../shared/model-interface/ShopProduct';
import { ApiService } from '../../../service/api.service';
import { SessionService } from '../../../service/session.service';
import { error } from 'console';
import { PopupService } from '../../../service/pop.up.data.service';
import { Route, Router  } from '@angular/router';
//METEMOS AL HIJO DENTRO DE IMPORTS
@Component({
  selector: 'shop-product-card',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './shop-product-card.html',
  styleUrl: './shop-product-card.scss',
})
export class ShopProductCard implements OnInit {
  //INYECTAMOS LA INFORMACION DEL COMPONENTE PADRE DE "PRODUCTS-PAGE.TS"
  @Input({ required: true }) shopProduct!: ShopProduct;

  constructor(private api: ApiService, private session: SessionService,private popup:PopupService, private router:Router) {

  }

  ngOnInit(): void {}

  buyItem(shopProductId: number) {
    const quantity = 1;
    const webUserId = this.session.getUserId();
    const endpoint = `/shop-cart/web-user-id=${webUserId}/shop-product-id=${shopProductId}/quantity=${quantity}`;
    this.api.post(endpoint).subscribe({
      next: (answer) => {
        console.log("agregado",answer)
        this.popup.success("Producto agregado")
      },
      error: (error) => {
        console.error(error);
      },
    });
  }

  goDetailPage(shopProductId: number) {
    this.router.navigate(['/products/detail/',shopProductId])
  }
}
