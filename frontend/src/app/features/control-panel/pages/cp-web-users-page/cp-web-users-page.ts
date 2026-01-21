import { Component } from '@angular/core';
import { BaseListComponent } from '../../../../shared/components/base-list-component/base-list-component';
import { WebUser } from '../../../../shared/model-interface/WebUser';
import { CommonModule, DatePipe } from '@angular/common';
import { MatIcon } from '@angular/material/icon';
import { CpUser } from '../../../../shared/model-interface/CpUser';
import Swal from 'sweetalert2';
import { take } from 'rxjs';

@Component({
  selector: 'cp-app-web-users-page',
  imports: [DatePipe, CommonModule, MatIcon],
  templateUrl: './cp-web-users-page.html',
  styleUrl: './../list-page.scss',
})
export class CpUsersPage extends BaseListComponent<WebUser> {
  protected readonly endpoint = '/summary-web-users';
  protected override detailRoutePath = 'web-user-detail';

  blockWebUser(webUserId: number, isBlocked: number) {
    console.log(isBlocked);
    const endpoint = `web-user/block-web-user/${webUserId}`;
    const accion = isBlocked ? 'Desbloquear' : 'Bloquear';
    const colorConfirmacion = isBlocked ? '#28a745' : '#dc3545';

    Swal.fire({
      title: `¿${accion} este usuario?`,
      text: `El acceso del usuario será ${isBlocked ? 'restaurado' : 'restringido'} de inmediato.`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: colorConfirmacion,
      cancelButtonColor: '#6c757d',
      confirmButtonText: `Sí, ${accion}`,
      cancelButtonText: 'Cancelar',
    }).then((result) => {
      if (result.isConfirmed) {
        this.api
          .post(endpoint, {})
          .pipe(take(1))
          .subscribe({
            next: () => {
              Swal.fire({
                title: '¡Hecho!',
                text: `Se ha cambiado el estado del usuario.`,
                icon: 'success',
                timer: 1500,
                showConfirmButton: false,
              });
              this.loadData();
            },
            error: (err) => {
              console.error('Error:', err);
              Swal.fire('Error', 'No se pudo completar la operación', 'error');
            },
          });
      }
    });
  }
}
