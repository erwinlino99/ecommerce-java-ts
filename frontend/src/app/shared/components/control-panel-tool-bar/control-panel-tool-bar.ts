import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router'; 
import { catchError, finalize, Observable, of, shareReplay } from 'rxjs';
import { ApiService } from '../../../service/api.service';
import { SessionService } from '../../../service/session.service';
import { CpUser } from '../../model-interface/CpUser';
import { CpIndex } from '../../model-interface/CpIndex';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-control-panel-tool-bar',
  standalone: true,
  imports: [CommonModule, MatIconModule, RouterModule], 
  templateUrl: './control-panel-tool-bar.html',
  styleUrl: './control-panel-tool-bar.scss',
})
export class ControlPanelToolBar implements OnInit {
  // Declaración de Observables
  adminUser$!: Observable<CpUser | null>;
  cpMenu$!: Observable<CpIndex[]>;

  loading = false;
  errorMsg = '';

  // Constructor clásico (Inyección de dependencias)
  constructor(
    private api: ApiService,
    private session: SessionService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const token = this.session.getSuperAdminToken();
    if (token) {
      this.fetchCpMenu();
      this.fetchAdminUser();
    } else {
      this.errorMsg = 'Sesión de administrador no válida.';
    }
  }

  private fetchCpMenu(): void {
    const endpoint="/cp-index"
    this.cpMenu$ = this.api.get<CpIndex[]>(endpoint).pipe(
      catchError((err) => {
        console.error('Error en cp-index:', err);
        this.errorMsg = 'Error al cargar el menú dinámico.';
        return of([] as CpIndex[]);
      }),
      shareReplay(1)
    );
  }

  private fetchAdminUser(): void {
    this.loading = true;
    this.adminUser$ = this.api.get<CpUser>('/cp-user').pipe(
      catchError((err) => {
        console.error('Error en admin-user:', err);
        return of(null);
      }),
      finalize(() => (this.loading = false)),
      shareReplay(1)
    );
  }

  logout(): void {
    this.session.clear();
    this.router.navigate(['/login']);
  }
}