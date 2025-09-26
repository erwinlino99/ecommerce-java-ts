import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';

// DECORADOR DEL MISMO COMPONENTE 
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [MatToolbarModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})

//TODAS LAS FUNCIONES QUE HABRAN DENTRO DEL MISMO
export class Home {

  goToRegister(){
    window.location.href = '/register';
  }
}
