import { Component } from '@angular/core';
import { BaseListComponent } from '../../../../shared/components/base-list-component/base-list-component';
import { CpShopOrder } from '../../../../shared/model-interface/CpShopOrder';
import { DatePipe, UpperCasePipe } from '@angular/common';
import { CommonModule } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'cp-app-orders-page',
  standalone: true,
  imports: [UpperCasePipe, DatePipe, CommonModule, MatIcon, CurrencyPipe],
  templateUrl: './cp-orders-page.html',
  styleUrl: './../list-page.scss',
})
export class CpOrdersPage extends BaseListComponent<CpShopOrder> {
  protected readonly endpoint = '/shop-order/all-summary-shop-orders';
// protected override forceSubscribe = true;
  protected override detailRoutePath = 'order-detail';
}
