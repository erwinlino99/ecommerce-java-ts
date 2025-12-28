import { Component } from '@angular/core';
import { ControlPanelToolBar } from '../control-panel-tool-bar/control-panel-tool-bar';
import { ControlPanelFooter } from '../control-panel-footer/control-panel-footer';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-control-panel-app-shell',
  standalone:true,
  imports: [ControlPanelToolBar,ControlPanelFooter,RouterOutlet],
  templateUrl: './control-panel-app-shell.html',
  styleUrl: './control-panel-app-shell.scss'
})
export class ControlPanelAppShell {

}
