import { Directive, OnInit, inject } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError, tap, take, switchMap } from 'rxjs/operators';
import { ApiService } from '../../../service/api.service';
import { Router, ActivatedRoute } from '@angular/router';

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
            console.error("❌ Error:", error);
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
    const URL = `${this.deletedEndpoint}/id=${id}`;
    this.api.delete(URL).pipe(take(1)).subscribe({
      next: () => {
        console.log(`🗑️ Registro ${id} borrado.`);
        this.loadData(); 
      },
      error: (err) => console.error("Error al borrar", err)
    });
  }
  goToDetail(id: number | string): void {
    this.router.navigate([this.detailRoutePath, id], { relativeTo: this.route });
  }
}