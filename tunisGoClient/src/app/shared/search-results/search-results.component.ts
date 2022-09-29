import { Component, OnInit } from '@angular/core';
import {MaisonService} from 'src/app/services/maisonService/maison.service'
import { TokenStorageService } from '../../services/tokenService/token-storage.service';
@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {
  role;
  listHouses : any;
  exist=false;

  constructor(private maisonService : MaisonService,
    private tokenStorage:TokenStorageService,) { }

  ngOnInit(): void {
    this.role = this.tokenStorage.getUser().roles;
    this.retreiveAllHouses();
  }

  //get tous les maisons published
  retreiveAllHouses(){
    this.maisonService.getAllPublished()
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
