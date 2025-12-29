import { Directive, OnInit, inject } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ApiService } from '../../../service/api.service';

@Directive()
export abstract class BaseListComponent<T> implements OnInit {
  // Inyección directa: ya no necesitamos declarar 'api' en cada hijo
  protected readonly api = inject(ApiService);

  public data$!: Observable<T[]>;
  // Propiedad abstracta: obliga al hijo a decirnos qué URL usar
  protected abstract readonly endpoint: string;
  ngOnInit(): void {
    this.loadData();
  }

  protected loadData(): void {
    this.data$ = this.api.get<T[]>(this.endpoint).pipe(
      tap((data) => {
        console.log(`TOTAL ${data.length} registros de ${this.endpoint}`);
        console.log("datos->",data);
      }),
      catchError((error) => {
        console.error('Error en la petición:', error);
        return of([]); // Devuelve array vacío para no romper el stream
      })
    );
  }
}
