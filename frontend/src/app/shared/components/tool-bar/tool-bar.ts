import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { catchError, finalize, Observable, of, shareReplay } from 'rxjs';
import { WebUser } from '../../model-interface/WebUser';
import { ShopIndex } from '../../model-interface/ShopIndex';
import { ApiService } from '../../../service/api.service';
import { SessionService } from '../../../service/session.service';
import { RouterLink } from '@angular/router';
import { MatIcon } from '@angular/material/icon';
import { MatDivider } from '@angular/material/divider';

@Component({
  selector: 'app-tool-bar',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule,RouterLink,MatIcon,MatDivider],
  templateUrl: './tool-bar.html',
  styleUrl: './tool-bar.scss',
})
export class ToolBar implements OnInit {
  userToken: string | null = null;
  webUser!: Observable<WebUser | null>;
  shopIndex!: Observable<ShopIndex[]>;

  loading = false;
  errorMsg = '';

  constructor(private api: ApiService, private session: SessionService, private router: Router) {}

  ngOnInit(): void {
    this.userToken = this.session.getToken();
    if (!this.userToken) {
      // this.router.navigate(['/']);
      return;
    }
    this.fetchShopIndex();
    this.fetchUser();
  }

  private fetchShopIndex(): void {
    this.shopIndex = this.api.get<ShopIndex[]>('shop-index').pipe(
      catchError((err) => {
        console.error('Error loading shop_index:', err);
        this.errorMsg = 'No se pudo cargar el menú principal.';
        return of([] as ShopIndex[]);
      }),

      shareReplay(1)
    );
  }

  private fetchUser(): void {
    this.loading = true;
    this.errorMsg = '';
    const endpoint="/web-user"
    this.webUser = this.api.get<WebUser>(endpoint).pipe(
      catchError((err) => {
        console.error('Error loading user:', err);
        this.errorMsg = 'No se pudo cargar la información del usuario.';
        return of(null);
      }),
      finalize(() => {
        this.loading = false;
      }),
      shareReplay(1)
    );
  }
}
