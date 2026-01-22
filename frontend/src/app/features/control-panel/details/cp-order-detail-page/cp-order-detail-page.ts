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
}
