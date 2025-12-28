import { Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private readonly TOKEN_KEY = 'client_token';
  private readonly SUPER_TOKEN_KEY = 'super_admin_token';

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  private isBrowser(): boolean {
    return isPlatformBrowser(this.platformId);
  }

  setWebUserToken(token: string): void {
    if (!this.isBrowser()) return;
    sessionStorage.setItem(this.TOKEN_KEY, token);
  }

  getClientToken(): string | null {
    if (!this.isBrowser()) return null;
    return sessionStorage.getItem(this.TOKEN_KEY);
  }

  setSuperAdminToken(token: string): void {
    if (this.isBrowser()) sessionStorage.setItem(this.SUPER_TOKEN_KEY, token);
  }

  getSuperAdminToken(): string | null {
    return this.isBrowser() ? sessionStorage.getItem(this.SUPER_TOKEN_KEY) : null;
  }

  clear(): void {
    if (!this.isBrowser()) return;
    sessionStorage.removeItem(this.TOKEN_KEY);
    sessionStorage.removeItem(this.SUPER_TOKEN_KEY);
  }

  hasSession(): boolean {
    return !!this.getClientToken();
  }
}
