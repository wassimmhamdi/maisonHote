import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ActivityService } from 'src/app/services/activityService/activity.service';
import {Activity} from '../../../models/activity'

@Component({
  selector: 'app-detail-activity',
  templateUrl: './detail-activity.component.html',
  styleUrls: ['./detail-activity.component.css']
})
export class DetailActivityComponent implements OnInit {
  idAct;
  imgsAct=[];
  indexImg=1;
  slide=false;
  currentAct: Activity;
  selectedValue: String;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private activityService : ActivityService) { }

  ngOnInit(): void {
    this.idAct = this.route.snapshot.paramMap.get('idAct');
    
    this.getActivityById();
    
  }


  getActivityById() {
    this.activityService.getActById(this.idAct).subscribe((res: Activity) => {
      this.currentAct = res;
      this.imgsAct=res.images;
      console.log(this.currentAct);
    });
  }

 
  

 plusDivs(n) {
  this.showDivs(this.indexImg += n);
  this.slide=true;
}

    showDivs(n) {
      var i;
      var x = document.getElementsByClassName("mySlide")as HTMLCollectionOf<HTMLElement>;
      if (n > x.length) {this.indexImg = 1}
      if (n < 1) {this.indexImg = x.length}
      for (i = 0; i < x.length; i++) {
        x[i].style.display = "none";  
      }
      x[this.indexImg-1].style.display = "block";  
    }

    
  
  
  
}
