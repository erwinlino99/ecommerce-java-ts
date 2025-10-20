import { Component } from '@angular/core';
import { ReactiveFormsModule, FormGroup, FormControl } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { ApiService } from '../service/api.service';
import { WebUser } from '../model_interface/WebUser';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-page',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
  ],
  templateUrl: './register-page.html',
  styleUrls: ['./register-page.scss'],
})
export class RegisterPage {
  //FIRST TIME IS A EMPTY ARRAY
  webUsers: WebUser[] = [];
  constructor(private api: ApiService, private router: Router) {}
  registerForm = new FormGroup({
    name: new FormControl<string>(''),
    last_name: new FormControl<string>(''),
    email: new FormControl<string>(''),
    password: new FormControl<string>(''),
  });

  TryToRegister(): void {
    //TODO THIS BAD ASF , NOT USING WEBUSER INTERFACE
    const newWebUser = this.registerForm.getRawValue();
    const endpoint = 'web_user';
    //TRYING TO GET ALL THE INFORMATION FROM BBDD AND BINDING TO OUR WEBUSER ARRAYS EMPTY
    this.api.get<WebUser[]>(endpoint).subscribe({
      next: (resp) => {
        this.webUsers = resp;
        console.log('NEW ->', newWebUser);
        //TRY TO SEARCH IF THIS newWebUser.email is on this.webUsers if this is in console.log goood else ba
        console.log('ALL -->', this.webUsers);
        const userExists = this.webUsers.some(
          (user) => user.email.toLowerCase() === newWebUser.email?.toLowerCase()
        );
        if (!userExists) {
          this.api.post(endpoint, newWebUser).subscribe({
            next: (resp) => {
              console.log('📥 Response from backend:', resp);
              alert('Data sent successfully! Check the console.');
            },
            error: (err) => {
              console.error("Error sending data to backend:', err");
            },
          });
        }
      },
      error: (err) => {
        console.error('ERROR GET /:' + endpoint, err);
        this.webUsers = [];
      },
    });
  }
  pingBackend(): void {
    this.api.get<string>('', { responseType: 'text' }).subscribe({
      next: (txt) => console.log('INFO FROM BACKEND :', txt),
      error: (err) => console.error('ERROR PING /:', err),
    });
  }
}
