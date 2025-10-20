import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class SessionService {
  private userIdKey = 'sessionUserId';

  setUserId(id: number): void {
    sessionStorage.setItem(this.userIdKey, String(id));
  }

  getUserId(): number | null {
    const v = sessionStorage.getItem(this.userIdKey);
    return v ? Number(v) : null;
  }

  clear(): void {
    sessionStorage.removeItem(this.userIdKey);
  }

  hasSession(): boolean {
    return !!sessionStorage.getItem(this.userIdKey);
  }
}
