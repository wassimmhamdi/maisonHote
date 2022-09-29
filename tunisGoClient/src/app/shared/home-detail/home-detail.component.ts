import { Component, OnInit , TemplateRef} from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import Swal from 'sweetalert2';
import { MaisonService } from 'src/app/services/maisonService/maison.service';
import { TokenStorageService } from '../../services/tokenService/token-storage.service';
import {Maison} from '../../models/maison'
@Component({
  selector: 'app-home-detail',
  templateUrl: './home-detail.component.html',
  styleUrls: ['./home-detail.component.css']
})
export class HomeDetailComponent implements OnInit {
  modalRef: BsModalRef;
  submittedUpdate = false;
  role: any;
  approuver= null;
  idHouse;
  imgsHouse=[];
  indexImg=1;
  slide=false;
  currentHouse: Maison;
  selectedValue: String;
  updateFormHouse: FormGroup;
exist=false;
capaciteTotale
delete=false;
  constructor(private router: Router,
    private route: ActivatedRoute,
    private tokenStorage: TokenStorageService,
    private maisonService : MaisonService,
    private modalService: BsModalService,
    private formBuilder: FormBuilder,) {
    
     }

  ngOnInit(): void {
    this.role = this.tokenStorage.getUser().roles[0].toString();
    this.idHouse = this.route.snapshot.paramMap.get('idHouse');
    this.getHouseById();
    this.updateFormHouse = this.formBuilder.group({
      nomMaison: [null, Validators.required],
      regionMaison: [null, [Validators.required]],
      adresseMaison: [null, [Validators.required]],
      descreptionMaison: [null, [Validators.required]],
      prixResMaison: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]],  
    });
  }
  get updateHouseControls() {
    return this.updateFormHouse.controls;
  }
  getHouseById() {
    this.maisonService.getHouse(this.idHouse).subscribe((res: Maison) => {
      this.exist=true;
      this.currentHouse = res;
      this.capaciteTotale=res.capacitéTotale;
      this.imgsHouse=res.images;
      this.approuver=res.approuver;
      console.log(res);
      this.updateFormHouse.setValue({
        nomMaison: this.currentHouse.nomMaison,
        regionMaison: this.currentHouse.regionMaison,
        adresseMaison:this.currentHouse.adresseMaison,
        descreptionMaison: this.currentHouse.descreptionMaison,
        prixResMaison:this.currentHouse.prixResMaison,  
      });
    });
  }

 
  //update maison
  editHouse() {
    this.submittedUpdate=true;
    if (this.updateFormHouse.invalid) {
      return;
    }
    let 
      data = {
        nomMaison: this.updateFormHouse.value.nomMaison,
        regionMaison: this.updateFormHouse.value.regionMaison,
        adresseMaison: this.updateFormHouse.value.adresseMaison,
        descreptionMaison: this.updateFormHouse.value.descreptionMaison,
        prixResMaison: this.updateFormHouse.value.prixResMaison,
      };
  
    this.maisonService
      .updateHouse(this.currentHouse.id, data)
      .subscribe(
        (response) => {
          console.log(data);
          console.log(response);
          Swal.fire('This house was modified successufuly', '', 'success');
          this.modalRef.hide();
          this.ngOnInit();
        },
        (error) => {
          console.log(error);
        }
        
      );
      
  }
//approuver maison
approuverHouse(){
  this.maisonService.approuverHouse(this.idHouse).subscribe(
    (response) => {
      console.log(response);
      Swal.fire('This house was successufuly approuved', '', 'success');
      
      this.ngOnInit();
    },
    (error) => {
      console.log(error);
    }
  )
}

//effacer maison
deleteHouse(){
  this.maisonService.deleteHouse(this.idHouse).subscribe ((response) => {
    console.log(response);
    this.delete=true;
    Swal.fire('This house was successufuly deleted', '', 'success');

  },
  (error) => {
    console.log(error);
  }
)
if(this.delete==true)
{this.router.navigate['/listMaisons']}
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

    openModal(template: TemplateRef<any>) {
      this.modalRef = this.modalService.show(template);
    }
    resetUpdateForm() {
      this.submittedUpdate=false;
      this.updateFormHouse.reset();
      this.modalRef.hide();
    }
}
