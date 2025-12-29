import { Directive, OnInit, inject } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { ApiService } from '../../../service/api.service';

@Directive()
export abstract class BaseListComponent<T> implements OnInit {
  protected readonly api = inject(ApiService);

  public data$!: Observable<T[]>;
  
  // Propiedad abstracta obligatoria
  protected abstract readonly endpoint: string;

  /**
   * OPCIÓN DE DEPURACIÓN: 
   * Si el hijo pone esto a 'true', la base hará un .subscribe() automático
   * para ver los datos en consola sin necesidad de HTML.
   */
  protected forceSubscribe: boolean = false;

  ngOnInit(): void {
    this.loadData();
  }

  protected loadData(): void {
    console.log(`--- INICIANDO PETICIÓN A: ${this.endpoint}`);

    // Configuramos el observable con los pipes de log
    const request$ = this.api.get<T[]>(this.endpoint).pipe(
      tap((data) => {
        console.log(`✅ ÉXITO [${this.endpoint}]: ${data?.length} registros.`);
        console.log("Datos recibidos ->", data);
      }),
      catchError((error) => {
        console.error(`❌ ERROR [${this.endpoint}]:`, error);
        return of([]);
      })
    );

    this.data$ = request$;

    // SI LA OPCIÓN ESTÁ ACTIVA, FORZAMOS LA SUSCRIPCIÓN
    if (this.forceSubscribe) {
      console.warn(`⚠️ MODO DEBUG ACTIVO: Suscripción manual forzada en ${this.endpoint}`);
      this.data$.subscribe({
        next: (data) => {
          // Ya lo muestra el 'tap', pero aquí podrías hacer lógica extra si quisieras
        }
      });
    }
  }
}