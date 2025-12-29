import { Component, inject } from '@angular/core'; // Añadido inject aquí
import { CommonModule } from '@angular/common';
import { BaseListComponent } from '../../../../shared/components/base-list-component/base-list-component';
import { ShopProduct } from '../../../../shared/model-interface/ShopProduct';
import { SessionService } from '../../../../service/session.service'; // Asegúrate de que la ruta sea correcta

@Component({
  selector: 'cp-app-products-page',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './cp-products-page.html',
  styleUrl: './cp-products-page.scss',
})
export class CpProductsPage extends BaseListComponent<ShopProduct> {
  // 1. El endpoint que usará la clase padre
  protected readonly endpoint = '/all-shop-products';

}