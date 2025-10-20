import { Component } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
//NEED TO IMPORT FORMGROUP Y FORMCONTROL TO RECOVER OUR INPUTS FROM USER FOR LOGIN IN
import { FormGroup, FormControl, ReactiveFormsModule, Validator, Validators } from '@angular/forms';
//IMPORT API SERVICE
import { ApiService } from '../service/api.service';
import { LoginWebUser } from '../model_interface/LoginWebUser';
import { SessionService } from '../service/session.service';
import { Router } from '@angular/router';
type LoginResponse = { id: number; [k: string]: any };
// DECORADOR DEL MISMO COMPONENTE
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './home.html',
  styleUrls: ['./home.scss'],
})

//TODAS LAS FUNCIONES QUE HABRAN DENTRO DEL MISMO
export class Home {
  emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

  constructor(
    private api: ApiService,
    private router:Router,
    private session: SessionService) {
  }
  //CREATE A FORMGROUP TO RECOVER THE DATA FROM THE INPUTS

  webUserLoginForm = new FormGroup({
    email: new FormControl<string>('', [Validators.required, Validators.pattern(this.emailRegex)]),
    password: new FormControl<string>(''),
  });

  //FUNCTIONS ->
  goToHomeApp() {
    const email = this.webUserLoginForm.get('email')?.value ?? '';
    const password = this.webUserLoginForm.get('password')?.value ?? '-';
    if (this.emailRegex.test(email) && password.length > 0) {
      const endpoint = 'login';
      let LoginWebUser: LoginWebUser = {id:3,email, password };
      console.log('LOGIN WEBUSER ->', LoginWebUser);
      //POST TO THE BACKEND
      this.api.post<LoginWebUser>(endpoint, LoginWebUser).subscribe({
        next:(value)=> {
          LoginWebUser.id=value.id;
          this.session.setUserId(value.id);  
          this.router.navigate(['/home']);   
        },
      });
    }
  }
  goToRegister() {
    window.location.href = '/register';
  }
}
