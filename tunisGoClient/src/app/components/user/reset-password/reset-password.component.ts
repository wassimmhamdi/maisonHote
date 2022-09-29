import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/userService/user.service';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  pwd : String;
reset=false;
resetToken : string;
  constructor(private userService: UserService) { 
  }

  ngOnInit(): void {
  }
  
  //reset Paswword
  ResetPwd(){
    console.log(this.pwd)
    this.reset=true;
    this.resetToken=localStorage.getItem("resetToken");
    let data = {
      password: this.pwd,
      resetToken : this.resetToken
  }
    this.userService.resetMdp(data).subscribe(
      res => {
      },
      (error) => {
        console.log(error);
      }
    );
    localStorage.clear();
  }
}
