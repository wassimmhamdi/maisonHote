import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../../../services/tokenService/token-storage.service';
declare var $:any;
@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  role: any;
  toggleBtn =false;
  constructor(private tokenStorage: TokenStorageService,) { }

  ngOnInit(): void {
    this.role = this.tokenStorage.getUser().roles[0].toString();
  }

  ngAfterViewInit(){
    $(".btn-toggle-menu").click(function() {
      $("#wrapper").toggleClass("toggled");
    });
}

}
