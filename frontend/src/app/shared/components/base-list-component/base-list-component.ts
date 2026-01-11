import { Directive, OnInit, inject } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, tap, take, switchMap } from 'rxjs/operators';
import { ApiService } from '../../../service/api.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Directive()
export abstract class BaseListComponent<T> implements OnInit {
  protected readonly api = inject(ApiService);
  protected router = inject(Router);
  protected route = inject(ActivatedRoute);
  public data$!: Observable<T[]>;
  protected abstract readonly endpoint: string;
  protected deletedEndpoint?: string;
  protected forceSubscribe: boolean = false;
  protected abstract detailRoutePath: string;

  private readonly refresh$ = new BehaviorSubject<void>(undefined);

  ngOnInit(): void {
    this.data$ = this.refresh$.pipe(
      switchMap(() => {
        return this.api.get<T[]>(this.endpoint).pipe(
          tap((data) => {
            if (this.forceSubscribe) {
              console.log(`✅ [${this.endpoint}] Datos:`, data);
            }
          }),
          catchError((error) => {
            console.error('❌ Error:', error);
            return of([]);
          })
        );
      })
    );
    if (this.forceSubscribe) {
      this.data$.subscribe();
    }
  }
  protected loadData(): void {
    this.refresh$.next();
  }

  deleteItem(id: number): void {
    Swal.fire({
      title: '¿Estás seguro?',
      text: `Se eliminará el registro seleccionado`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#d33', 
      cancelButtonColor: '#3085d6', 
      confirmButtonText: 'Sí, borrarlo',
      cancelButtonText: 'No, mantenerlo',
    }).then((result) => {
      if (result.isConfirmed) {
        const URL = `${this.deletedEndpoint}/id=${id}`;
        this.api
          .delete(URL)
          .pipe(take(1))
          .subscribe({
            next: () => {
              Swal.fire('¡Borrado!', 'El registro ha sido eliminado correctamente.', 'success');
              this.loadData();
            },
            error: (err) => {
              console.error('Error al borrar', err);
              Swal.fire('Error', 'No se pudo eliminar el registro. Inténtalo más tarde.', 'error');
            },
          });
      }
    });
  }

  goToDetail(id: number | string): void {
    this.router.navigate([this.detailRoutePath, id], { relativeTo: this.route });
  }
}
