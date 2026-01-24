import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ErrorService {
  // Ahora guardamos el mensaje. Si es null, la pantalla está oculta.
  private errorMessage$ = new BehaviorSubject<string | null>(null);
  public errorState$ = this.errorMessage$.asObservable();

  // Recibe el mensaje por parámetro
  show(message: string) {
    this.errorMessage$.next(message);
  }

  hide() {
    this.errorMessage$.next(null);
  }
}