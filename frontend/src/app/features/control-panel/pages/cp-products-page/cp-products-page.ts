import { Component, inject } from '@angular/core'; // Añadido inject aquí
import { CommonModule } from '@angular/common';
import { BaseListComponent } from '../../../../shared/components/base-list-component/base-list-component';
import { ShopProduct } from '../../../../shared/model-interface/ShopProduct';
import { SessionService } from '../../../../service/session.service'; // Asegúrate de que la ruta sea correcta
import { MatIcon } from '@angular/material/icon';
import Swal from 'sweetalert2';
import { take } from 'rxjs';

@Component({
  selector: 'cp-app-products-page',
  standalone: true,
  imports: [CommonModule, MatIcon],
  templateUrl: './cp-products-page.html',
  styleUrl: './../list-page.scss',
})
export class CpProductsPage extends BaseListComponent<ShopProduct> {
  // 1. El endpoint que usará la clase padre
  protected readonly endpoint = '/all-shop-products';
  protected override detailRoutePath = 'product-detail';
  protected override deletedEndpoint = '/shop-product/delete';
  protected override forceSubscribe = true;
  currentTab: 'active' | 'deleted' = 'active';
  setTab(tab: 'active' | 'deleted') {
    this.currentTab = tab;
  }
  getFilteredProducts(productos: ShopProduct[]): ShopProduct[] {
    if (this.currentTab === 'active') {
      return productos.filter((p) => p.deleted === null);
    }
    return productos.filter((p) => p.deleted !== null);
  }

restoreItem(shopProductId: number) {
    const endpoint = `shop-product/restore/${shopProductId}`;

    Swal.fire({
        title: '¿RESTAURAR PRODUCTO?',
        text: 'Volverá al catálogo activo',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'SÍ', // Texto simple
        cancelButtonText: 'NO',
        confirmButtonColor: '#4ade80', // Color verde (success) directo para asegurar
        cancelButtonColor: '#475569',  // Color gris (border-ui) directo
        reverseButtons: true
    }).then((result) => {
        if (result.isConfirmed) {
            this.api.post(endpoint, {})
                .pipe(take(1))
                .subscribe({
                    next: () => {
                        Swal.fire({
                            title: '¡RESTABLECIDO!',
                            text: 'Operación finalizada.',
                            icon: 'success',
                            confirmButtonText: 'OK', // Botón de OK añadido
                            confirmButtonColor: '#38bdf8' // Color azul (accent)
                        });
                        this.loadData();
                    },
                    error: () => {
                        Swal.fire({
                            title: 'ERROR',
                            text: 'No se pudo procesar',
                            icon: 'error',
                            confirmButtonText: 'OK'
                        });
                    }
                });
        }
    });
}
}
