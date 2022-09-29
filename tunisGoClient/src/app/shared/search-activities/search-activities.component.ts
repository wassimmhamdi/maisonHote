import { Component, OnInit } from '@angular/core';
import {ActivityService} from 'src/app/services/activityService/activity.service'
import { TokenStorageService } from '../../services/tokenService/token-storage.service';
@Component({
  selector: 'app-search-activities',
  templateUrl: './search-activities.component.html',
  styleUrls: ['./search-activities.component.css']
})
export class SearchActivitiesComponent implements OnInit {
  role;
  listActivities : any;
  exist=false;
  constructor(private activityService : ActivityService,
    private tokenStorage:TokenStorageService,) { }

  ngOnInit(): void {
    this.role = this.tokenStorage.getUser().roles;
    this.retreiveAllActivities();
  }


  //get tous les activités published
  retreiveAllActivities(){
    this.activityService.getAllPublished()
    .subscribe(
      data => {
        this.exist=true;
        this.listActivities = data;
        console.log(data);
        
      },
      error => {
        console.log(error);
      });
  }
}
