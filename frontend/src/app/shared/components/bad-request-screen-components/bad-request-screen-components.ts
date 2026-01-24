import { Component, inject } from '@angular/core';
import { CommonModule, AsyncPipe, NgIf } from '@angular/common';
import { ErrorService } from '../../../service/error.service';

@Component({
  selector: 'app-bad-request-screen-components',
  standalone: true,
  imports: [CommonModule, AsyncPipe, NgIf],
  templateUrl: './bad-request-screen-components.html',
  styleUrl: './bad-request-screen-components.scss'
})
export class BadRequestScreenComponents {
  public errorService = inject(ErrorService);
}