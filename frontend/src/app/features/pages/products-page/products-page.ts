import { Component, OnInit } from '@angular/core';
import { ShopProduct } from '../../../shared/model-interface/ShopProduct';
import { SessionService } from '../../../service/session.service';
import { ApiService } from '../../../service/api.service';
import { Route, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ShopProductCard } from '../../components/shop-product-card/shop-product-card';



@Component({
  selector: 'app-products-page',
  standalone: true,
  imports: [CommonModule,ShopProductCard],
  templateUrl: './products-page.html',
  styleUrl: './products-page.scss',
})


export class ProductsPage implements OnInit {
  shopProduct: ShopProduct[] = [];

  constructor(private session: SessionService, private api: ApiService) {}
  ngOnInit() {
    this.getShopProducts();
    // console.log("DESDE EL BACK >",this.shopProduct);
  }

  private getShopProducts() {
    const endoint = 'shop-product';
    this.api.get<ShopProduct[]>(endoint).subscribe({
      next: (items) => {
        this.shopProduct = items;
        console.log('DESDE EL BACK >', this.shopProduct);
      },
    });
  }
}
