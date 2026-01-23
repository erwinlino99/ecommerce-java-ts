import { Component } from '@angular/core';
import { BaseDetailComponent } from '../../../../shared/components/base-detail-component/base-detail-component';
import { CpShopOrder } from '../../../../shared/model-interface/CpShopOrder';
import { FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule, CurrencyPipe, DatePipe } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
@Component({
  selector: 'app-cp-order-detail-page',
  imports: [ReactiveFormsModule, CurrencyPipe, CommonModule, DatePipe, MatIcon],
  standalone: true,
  templateUrl: './cp-order-detail-page.html',
  styleUrl: './../detail-page.scss',
})
export class CpOrderDetailPage extends BaseDetailComponent<CpShopOrder> {
  protected override endpoint = 'shop-order';
  protected override idParamName = 'orderIdDetail';
  protected override createForm(): FormGroup {
    return this.fb.group({
      // NO DEVOLVEMOS NADA
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
