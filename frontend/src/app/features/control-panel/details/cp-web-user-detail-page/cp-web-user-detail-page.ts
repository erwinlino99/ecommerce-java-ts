import { Component } from '@angular/core';
import { BaseDetailComponent } from '../../../../shared/components/base-detail-component/base-detail-component';
import { WebUser } from '../../../../shared/model-interface/WebUser';
import { ReactiveFormsModule, Validators, FormGroup } from '@angular/forms';
import { CpUsersPage } from '../../pages/cp-web-users-page/cp-web-users-page';
import { CpUser } from '../../../../shared/model-interface/CpUser';
import { CommonModule, DatePipe } from '@angular/common';

@Component({
  selector: 'app-cp-web-user-detail-page',
  imports: [DatePipe, CommonModule, ReactiveFormsModule],
  templateUrl: './cp-web-user-detail-page.html',
  styleUrl: './../detail-page.scss',
})
export class CpWebUserDetailPage extends BaseDetailComponent<WebUser> {
  protected override endpoint = 'web-user';
  // EL NOMBRE DEL PARÁMETRO EN TU RUTAS (debe coincidir con app.routes.ts)
  protected override idParamName = 'webUserIdDetail';


  protected override createForm(): FormGroup {
    return this.fb.group({
      id: [{ value: null, disabled: true }],
      name: ['', [Validators.required]],
      lastName: ['', [Validators.required]], // Volvemos a lastName
      email: ['', [Validators.required, Validators.email]],
      cif: [''],
      isActive: [1], // Volvemos a camelCase
      isBlocked: [0], // Añadido por coherencia
      deleted: [null],
    });
  }



  goImpersonate(webUserId: number) {
    console.log('IMPERSONANDO AL USUARIO', webUserId);
    const body={
      webUserId:webUserId
    };
    const endpoint="web-user/impersonate"
    this.api.post(endpoint,body).subscribe({
      next:((data=>{
        
      }))
    })
  }
}
