import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-tinder-interface',
  templateUrl: './tinder-interface.component.html',
  styleUrls: ['./tinder-interface.component.css']
})
export class TinderInterfaceComponent implements OnInit {

  constructor() { }
  indexImg=1;
  slide=false;
  ngOnInit(): void {
  }
  //code pour le slide show
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
