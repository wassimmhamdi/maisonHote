import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../services/authService/auth.service';
import { TokenStorageService } from '../../../services/tokenService/token-storage.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  registerFormUser: FormGroup;
  isSuccessful = false;
  submitted = false;
  submittedLogin = false;
  isSignUpFailed = false;
  errorMessage = '';
  loginFormUser: FormGroup;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: any;
  
  constructor(
    private router: Router,
    private authService: AuthService,
    private tokenStorage: TokenStorageService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    
    this.registerFormUser = this.formBuilder.group({
      nom: [null, Validators.required],
      prenom: [null, [Validators.required]],
      phone: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        Validators.minLength(8),]],
      email: [null, Validators.required],
      password: [null, [Validators.required, Validators.minLength(8)]]});

      this.loginFormUser = this.formBuilder.group({
        email: [null, Validators.required],
        password: [null, [Validators.required, Validators.minLength(8)]]});
    if (
      this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles[0].toString();
      console.log("roleeee", this.tokenStorage.getUser().roles[0].toString())
    }
    
  }
  get registerUserControls() {
    return this.registerFormUser.controls;
  }
  get loginUserControls() {
    return this.loginFormUser.controls;
  }

  onRegister(): void {
    this.submitted = true;
    if (this.registerFormUser.invalid) {
      return;
    }
    this.authService.register(this.registerFormUser).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
  onLogin(): void {
    this.submittedLogin=true;
    this.authService.login(this.loginFormUser).subscribe(
      data => {
        console.log(this.loginFormUser)
        this.tokenStorage.saveToken(data.accessToken);
        this.tokenStorage.saveUser(data);

        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
  reloadPage(): void {
    window.location.reload();
  }

  
  logout(){
    this.tokenStorage.signOut();
    this.router.navigate([''], { replaceUrl: true });
    window.location.reload();
  }

}
