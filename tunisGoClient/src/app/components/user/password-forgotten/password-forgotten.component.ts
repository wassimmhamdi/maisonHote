import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/userService/user.service';


@Component({
  selector: 'app-password-forgotten',
  templateUrl: './password-forgotten.component.html',
  styleUrls: ['./password-forgotten.component.css']
})
export class PasswordForgottenComponent implements OnInit {
  send = false;
  email : String;
  resetToken: string;
  errorMessage = '';
  constructor(private userService: UserService,) { }

  ngOnInit(): void {
  }
  //generate random string
  stringGen(len) {
    var text = '';
    
    var charset = "abcdefghijklmnopqrstuvwxyz0123456789";
    
    for (var i = 0; i < len; i++)
      text += charset.charAt(Math.floor(Math.random() * charset.length));
    
    return text;
  }
  //reset Paswword
  sendResetPwdMail(){
    this.send=true;
    console.log(this.email);
    this.resetToken=this.stringGen(32);
    localStorage.setItem('resetToken', this.resetToken);
    let data = {
        email: this.email,
        resetToken : this.resetToken
    }
    this.userService.sendMailResetMdp(data).subscribe(
      res => {
        
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
