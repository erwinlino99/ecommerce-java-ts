import { Component, Input, input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ShopProduct } from '../../../shared/model-interface/ShopProduct';


//METEMOS AL HIJO DENTRO DE IMPORTS
@Component({
  selector: 'shop-product-card',
  standalone:true,
  imports: [CommonModule],
  templateUrl: './shop-product-card.html',
  styleUrl: './shop-product-card.scss'
})
export class ShopProductCard {
  //INYECTAMOS LA INFORMACION DEL COMPONENTE PADRE DE "PRODUCTS-PAGE.TS"
  @Input({ required: true }) shopProduct!: ShopProduct;
}
