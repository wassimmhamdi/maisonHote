import { Component, OnInit } from '@angular/core';
import {ActivityService} from 'src/app/services/activityService/activity.service'
@Component({
  selector: 'app-demande-activity',
  templateUrl: './demande-activity.component.html',
  styleUrls: ['./demande-activity.component.css']
})
export class DemandeActivityComponent implements OnInit {
  listActivitiesNotPublished : any;
  exist=false;

  constructor(private activityService : ActivityService) { }

  ngOnInit(): void {
    this.retreiveAllNotPublishedHouses()
  }


  retreiveAllNotPublishedHouses(){
    this.activityService.getAllNotPublished()
    .subscribe(
      data => {
        this.listActivitiesNotPublished = data;
        this.exist=true;
        console.log(data);
        
      },
      error => {
        console.log(error);
      });
  }
}
