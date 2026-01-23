import { Component } from '@angular/core';
import { BaseListComponent } from '../../../../shared/components/base-list-component/base-list-component';
import { CpShopOrder } from '../../../../shared/model-interface/CpShopOrder';
import { DatePipe, UpperCasePipe, CommonModule, CurrencyPipe } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { take } from 'rxjs'; // Importante para la gestión de memoria
import Swal from 'sweetalert2'; // Asegúrate de tenerlo importado

@Component({
  selector: 'cp-app-orders-page',
  standalone: true,
  imports: [UpperCasePipe, DatePipe, CommonModule, MatIcon, CurrencyPipe],
  templateUrl: './cp-orders-page.html',
  styleUrl: './../list-page.scss',
})
export class CpOrdersPage extends BaseListComponent<CpShopOrder> {
  protected readonly endpoint = '/shop-order/all-summary-shop-orders';
  protected override detailRoutePath = 'order-detail';
  protected override forceSubscribe = true;
  cancelOrderId(shopOrderId: number) {
    const endpoint = `shop-order/cancel/${shopOrderId}`;

    Swal.fire({
      title: '¿Confirmar cancelación?',
      text: `¿Estás seguro de que deseas cancelar el pedido #${shopOrderId}? Esta acción no se puede deshacer.`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#dc3545',
      cancelButtonColor: '#6c757d',
      confirmButtonText: 'Sí, cancelar pedido',
      cancelButtonText: 'Mantener pedido',
    }).then((result) => {
      if (result.isConfirmed) {
        this.api
          .post(endpoint, {})
          .pipe(take(1))
          .subscribe({
            next: () => {
              Swal.fire({
                title: 'Pedido Cancelado',
                text: 'El estado del pedido ha sido actualizado a cancelado.',
                icon: 'success',
                timer: 2000,
                showConfirmButton: false,
              });

              this.loadData();
            },
            error: (err) => {
              console.error('Error al cancelar pedido:', err);
              Swal.fire({
                title: 'Error',
                text: 'No se pudo procesar la cancelación del pedido.',
                icon: 'error',
              });
            },
          });
      }
    });
  }

  downloadInvoice(shopOrderId: number | undefined) {
    if (!shopOrderId) return;
    const endpoint = `file/download-invoice/${shopOrderId}`;
    this.api.get(endpoint, { responseType: 'blob' }).subscribe({
      next: (res: any) => {
        const file = new Blob([res], { type: 'application/pdf' });
        const fileURL = URL.createObjectURL(file);
        const link = document.createElement('a');
        link.href = fileURL;
        link.download = `Factura_ECC_${shopOrderId}.pdf`;
        link.click();
        URL.revokeObjectURL(fileURL);
      },
      error: (err) => {
        console.error('Error al descargar la factura:', err);
      },
    });
  }
}
