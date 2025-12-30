import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';
import { BaseDetailComponent } from '../../../../shared/components/base-detail-component/base-detail-component';
import { ShopProduct } from '../../../../shared/model-interface/ShopProduct';

@Component({
  selector: 'app-cp-product-detail-page',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './cp-product-detail-page.html',
    styleUrl: './../detail-page.scss',
})
export class CpProductDetailPage extends BaseDetailComponent<ShopProduct> {
  // EL ENDPOINT QUE TU API ESPERA (siguiendo tu interfaz)
  protected override endpoint = 'shop-product-id';
  // EL NOMBRE DEL PARÁMETRO EN TU RUTAS (debe coincidir con app.routes.ts)
  protected override idParamName = 'productIdDetail';
  /**
   * MÉTODO OBLIGATORIO: Definimos la estructura del formulario basada en ShopProduct.
   * Usamos los mismos nombres de la interfaz para que el patchValue funcione solo.
   */
  protected override createForm(): FormGroup {
    return this.fb.group({
      id: [{ value: null, disabled: true }], // El ID suele ser solo lectura
      name: ['', [Validators.required, Validators.minLength(3)]],
      shortDescription: ['', [Validators.required]],
      description: [''],
      brandName: ['', [Validators.required]],
      shopProductMeasurement: [''],
      currentStock: [0, [Validators.required, Validators.min(0)]],
      price: [0, [Validators.required, Validators.min(0.01)]],
      deleted: [null],
    });
  }
}
