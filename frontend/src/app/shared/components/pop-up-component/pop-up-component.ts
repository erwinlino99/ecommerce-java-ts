import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PopupService } from '../../../service/pop.up.data.service';

@Component({
  selector: 'app-pop-up',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div *ngIf="popupService.popup() as p" class="popup" [ngClass]="p.type">
      {{ p.message }}
    </div>
  `,
  styleUrls: ['./pop-up-component.scss'],
})
export class PopUpComponent {
  constructor(public popupService: PopupService) {}
}
