import { Component } from '@angular/core';
import { ToolBar } from '../tool-bar/tool-bar';
import { AppFooter } from '../app-footer/app-footer';
import { RouterOutlet } from '@angular/router';


@Component({
  selector: 'app-app-shell',
  standalone:true,
  imports: [ToolBar,AppFooter,RouterOutlet],
  templateUrl: './app-shell.html',
  styleUrl: './app-shell.scss'
})
export class AppShell {

}
