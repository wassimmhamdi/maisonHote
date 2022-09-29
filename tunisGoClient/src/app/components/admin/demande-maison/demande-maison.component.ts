import { Component, OnInit } from '@angular/core';
import {MaisonService} from 'src/app/services/maisonService/maison.service'

@Component({
  selector: 'app-demande-maison',
  templateUrl: './demande-maison.component.html',
  styleUrls: ['./demande-maison.component.css']
})
export class DemandeMaisonComponent implements OnInit {

  listHousesNotPublished : any;
  exist=false;

  constructor(private maisonService : MaisonService) { }
  


  ngOnInit(): void {
    this.retreiveAllNotPublishedHouses();
  }


  retreiveAllNotPublishedHouses(){
    this.maisonService.getAllNotPublished()
    .subscribe(
      data => {
        this.listHousesNotPublished = data;
        this.exist=true;
        console.log(data);
        
      },
      error => {
        console.log(error);
      });
  }
}
