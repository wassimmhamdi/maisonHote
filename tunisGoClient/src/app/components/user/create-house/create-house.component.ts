import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import {MaisonService} from 'src/app/services/maisonService/maison.service';
import { TokenStorageService } from '../../../services/tokenService/token-storage.service';
import { Maison} from '../../../models/maison';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-create-house',
  templateUrl: './create-house.component.html',
  styleUrls: ['./create-house.component.css']
})
export class CreateHouseComponent implements OnInit {
  next=false;
  save=false;
  url:any;
  currenHouse : Maison;
addFormHouse: FormGroup;
images:File[];
idUserConnected:any;
addHouseToken: string;
submittedAddHouse=false;
selectedFiles?: FileList;
imageDeleteFrom: FormGroup;
  imageurls =[];
  base64String: string;
  name: string;
  imagePath: string;

  constructor(private formBuilder: FormBuilder,
    private maisonService : MaisonService,
    private tokenStorage:TokenStorageService,
    private router : Router) { }

  ngOnInit(): void {
    this.addFormHouse = this.formBuilder.group({
      nomMaison: [null, Validators.required],
      regionMaison: [null, [Validators.required]],
      adresseMaison: [null, [Validators.required]],
      descreptionMaison: [null, [Validators.required]],
      prixResMaison: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]], 
      nbrChambre: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]],
      SalleDeBain: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]], 
      capaciteTotale: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]],  
    });
  }
  get addHouseControls() {
    return this.addFormHouse.controls;
  }
   //generate random string
   stringGen(len) {
    var text = '';
    
    var charset = "abcdefghijklmnopqrstuvwxyz0123456789";
    
    for (var i = 0; i < len; i++)
      text += charset.charAt(Math.floor(Math.random() * charset.length));
    
    return text;
  }


//enregister la maison
goNext(){
  this.submittedAddHouse=true;
  
this.idUserConnected = this.tokenStorage.getUser().id;
  this.addHouseToken=this.stringGen(32);
  //localStorage.setItem('addActToken', this.addActToken);
  if (this.addFormHouse.invalid) {
    return;
  }

  let 
    data = {
      idUser : this.idUserConnected,
      nomMaison: this.addFormHouse.value.nomMaison,
      regionMaison: this.addFormHouse.value.regionMaison,
      adresseMaison: this.addFormHouse.value.adresseMaison,
      descreptionMaison: this.addFormHouse.value.descreptionMaison,
      prixResMaison: this.addFormHouse.value.prixResMaison,
      nbrChambre: this.addFormHouse.value.nbrChambre,
      SalleDeBain: this.addFormHouse.value.SalleDeBain, 
      capacitéTotale:this.addFormHouse.value.capaciteTotale,
      //approuver:false,
      //isHouseRes: false,
      addHouseToken : this.addHouseToken,
      
    };
  
console.log(data)

  this.maisonService.addHouse(data).subscribe((res) => {
      
    });

   
  this.next=true;
}

//get current House 
getCurrentHouse(){
  this.save=true;
  this.maisonService.getHouseByToken(this.addHouseToken).subscribe((res) => {
    this.currenHouse=res;
    console.log('current house',res);
  });
}

  //select images et les enregistrer
selectFiles(event): void {

  this.selectedFiles = event.target.files;
  if (event.target.files && event.target.files[0]) {
    var filesAmount = event.target.files.length;
    for (let i = 0; i < filesAmount; i++) {
      var reader = new FileReader();
      reader.onload = (event: any) => {
        this.imageurls.push({ base64String: event.target.result, });
      }
      reader.readAsDataURL(event.target.files[i]);
    }
  }
}
uploadFiles(): void {

  if (this.selectedFiles) {
    for (let i = 0; i < this.selectedFiles.length; i++) {
      this.upload(this.currenHouse.id, this.selectedFiles[i]);
    }
    
  }
  
  Swal.fire('House added succesuffuly!', '', 'success');
      
  this.router.navigate['/listMaisons']
}
upload(id, file: File): void {

    this.maisonService.addImg(id,file).subscribe()
}

// pour effacer une image selectionné
removeImageEdit(i, imagepath) {
  this.imageDeleteFrom.value.id = i;
  this.imageDeleteFrom.value.ImagePath = imagepath;
}

removeImage(i) {
  this.imageurls.splice(i, 1);
}

}
