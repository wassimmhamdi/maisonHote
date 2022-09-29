import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/userService/user.service';

import {User} from '../../../models/user'
@Component({
  selector: 'app-detail-user',
  templateUrl: './detail-user.component.html',
  styleUrls: ['./detail-user.component.css']
})
export class DetailUserComponent implements OnInit {
  idUser;
  exist=false;
  currentUser: User;
  selectedValue: String;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private userService : UserService) { }

  ngOnInit(): void {
    this.idUser = this.route.snapshot.paramMap.get('idUser');
    this.getUserById();
  }


  getUserById() {
    this.userService.getUser(this.idUser).subscribe((res: User) => {
      this.exist=true;
      this.currentUser = res;
      console.log(this.currentUser);
    });
  }
}
