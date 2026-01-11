import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PopUpComponent } from './shared/components/pop-up-component/pop-up-component';
import { LoadingScreenComponents } from './shared/components/loading-screen-components/loading-screen-components';


@Component({
  selector: 'app-root',
   standalone: true,
  imports: [RouterOutlet,PopUpComponent,LoadingScreenComponents],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class App {
  protected readonly title = signal('frontend');
}
