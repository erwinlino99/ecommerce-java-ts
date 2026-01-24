import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { BaseDetailComponent } from '../../../../shared/components/base-detail-component/base-detail-component';
import { ShopProduct } from '../../../../shared/model-interface/ShopProduct';
import { BaseSelectableComponent } from '../../../../shared/components/base-selectable-component/base-selectable-component';
import { MatIcon } from '@angular/material/icon';

@Component({
  selector: 'app-cp-product-detail-page',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, BaseSelectableComponent,MatIcon], // Integración del selector
  templateUrl: './cp-product-detail-page.html',
  styleUrl: './../detail-page.scss',
})
export class CpProductDetailPage extends BaseDetailComponent<ShopProduct> {
  protected override endpoint = 'shop-product-id';
  protected override idParamName = 'productIdDetail';

  protected override createForm(): FormGroup {
    return this.fb.group({
      id: [{ value: null, disabled: true }],
      name: ['', [Validators.required]],
      brandName: [{ value: '', disabled: true }],
      measurementName: [{ value: '', disabled: true }],
      shopProductBrandId: [null, [Validators.required]],
      price: [0, [Validators.required]],
      currentStock: [0, [Validators.required]],
      shopProductMeasurement: [''],
      measurementUnit: [''],
      shortDescription: ['', [Validators.required]],
      description: [''],
      deleted: [null],
    });
  }
}