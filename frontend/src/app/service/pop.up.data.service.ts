import { Injectable, signal } from '@angular/core';

export type PopupType = 'success' | 'error';

export interface PopupData {
  message: string;
  type: PopupType;
}

@Injectable({ providedIn: 'root' })
export class PopupService {
  popup = signal<PopupData | null>(null);

  private hideTimer?: any;

  success(message: string, ms = 1100) {
    this.show({ message, type: 'success' }, ms);
  }

  error(message: string, ms = 1100) {
    this.show({ message, type: 'error' }, ms);
  }

  private show(data: PopupData, ms: number) {
    clearTimeout(this.hideTimer);

    this.popup.set(null);

    queueMicrotask(() => {
      this.popup.set(data);
      this.hideTimer = setTimeout(() => this.popup.set(null), ms);
    });
  }
}
