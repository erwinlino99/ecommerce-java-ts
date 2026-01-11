import { Component, inject } from '@angular/core';
import { CommonModule, AsyncPipe, NgIf } from '@angular/common'; // Agrega AsyncPipe y NgIf
import { LoadingService } from '../../../service/loading.service';

@Component({
  selector: 'app-loading-screen',
  standalone: true,
  imports: [CommonModule, AsyncPipe, NgIf], 
  templateUrl: './loading-screen-components.html',
  styleUrls: ['./loading-screen-components.scss']
})
export class LoadingScreenComponents {
  public loadingService = inject(LoadingService);
}