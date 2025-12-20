import { Component, OnInit } from '@angular/core';
import { ShopProduct } from '../../../shared/model-interface/ShopProduct';
import { ApiService } from '../../../service/api.service';
import { error } from 'console';
import { ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-shop-product-detail',
  imports: [],
  templateUrl: './shop-product-detail.html',
  styleUrl: './shop-product-detail.scss',
})
export class ShopProductDetail implements OnInit {
  shopProduct?: ShopProduct;

  constructor(private api: ApiService, private route: ActivatedRoute) {}
  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('shopProductId'));
    const endpoint = `shop-product-id=${id}`;
    console.log("endpoint ",endpoint);
    
    this.api.get<ShopProduct>(endpoint).subscribe({
      next: (item) => {
        this.shopProduct = item;
        console.log(item)
        
      },
      error: (err) => {
        //TODO EN CASO DE QUE ME DEVUELVA ERROR HAY QUE HACER UNA PAGINA DE ERRORES
        console.error("LOS PEPES ",err);
      },
    });


  }
}
