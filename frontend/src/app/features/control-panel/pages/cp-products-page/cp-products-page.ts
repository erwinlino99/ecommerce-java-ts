import { Component, inject } from '@angular/core'; // Añadido inject aquí
import { CommonModule } from '@angular/common';
import { BaseListComponent } from '../../../../shared/components/base-list-component/base-list-component';
import { ShopProduct } from '../../../../shared/model-interface/ShopProduct';
import { SessionService } from '../../../../service/session.service'; // Asegúrate de que la ruta sea correcta
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'cp-app-products-page',
  standalone: true,
  imports: [CommonModule,MatIcon],
  templateUrl: './cp-products-page.html',
   styleUrl: './../list-page.scss',
})
export class CpProductsPage extends BaseListComponent<ShopProduct> {
  // 1. El endpoint que usará la clase padre
  protected readonly endpoint = '/all-shop-products';
  protected override detailRoutePath = 'product-detail';

}