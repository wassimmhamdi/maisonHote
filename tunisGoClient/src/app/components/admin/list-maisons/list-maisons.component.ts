import { Component, OnInit } from '@angular/core';
import {MaisonService} from 'src/app/services/maisonService/maison.service'
import { TokenStorageService } from '../../../services/tokenService/token-storage.service';
@Component({
  selector: 'app-list-maisons',
  templateUrl: './list-maisons.component.html',
  styleUrls: ['./list-maisons.component.css']
})
export class ListMaisonsComponent implements OnInit {
  role;
  idConnected;
  listHouses : any;
  exist=false;
  constructor(private maisonService : MaisonService,
    private tokenStorage:TokenStorageService,) { }

  ngOnInit(): void {
    this.role = this.tokenStorage.getUser().roles;
    this.idConnected = this.tokenStorage.getUser().id;
    this.retreiveAllHouses();
  }
//get tous les maisons 
retreiveAllHouses(){
  this.maisonService.getHousePerUser(this.idConnected)
  .subscribe(
    data => {
      this.exist=true;
      this.listHouses = data;
      console.log(data);
      
    },
    error => {
      console.log(error);
    });
}
}
