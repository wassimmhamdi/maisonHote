import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import {ActivityService} from 'src/app/services/activityService/activity.service';
import { TokenStorageService } from '../../../services/tokenService/token-storage.service';
import { Activity} from '../../../models/activity';
import Swal from 'sweetalert2';
@Component({
  selector: 'app-create-activity',
  templateUrl: './create-activity.component.html',
  styleUrls: ['./create-activity.component.css']
})
export class CreateActivityComponent implements OnInit {
next=false;
save=false;
url:any;
activities : Activity[];
currenAct : Activity;
addFormActivity: FormGroup;
images:File[];
idUserConnected:any;
addActToken: string;
submittedAddAct=false;
selectedFiles?: FileList;
imageDeleteFrom: FormGroup;
  imageurls =[];
  base64String: string;
  name: string;
  imagePath: string;
constructor(private formBuilder: FormBuilder,
  private activityService : ActivityService,
  private tokenStorage:TokenStorageService) { }

  ngOnInit(): void {
    this.addFormActivity = this.formBuilder.group({
      name: [null, Validators.required],
      description: [null, [Validators.required]],
      type: [null, [Validators.required]],
      region: [null, [Validators.required]],
      prix: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]],  
        max_participants: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]],
        min_participants: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        ]],
    });
  }

  get addActivityControls() {
    return this.addFormActivity.controls;
  }
   //generate random string
   stringGen(len) {
    var text = '';
    
    var charset = "abcdefghijklmnopqrstuvwxyz0123456789";
    
    for (var i = 0; i < len; i++)
      text += charset.charAt(Math.floor(Math.random() * charset.length));
    
    return text;
  }
//select files
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
      this.upload(this.currenAct.id, this.selectedFiles[i]);
    }
    
  }
  
  Swal.fire('Membre ajouté avec succès!', '', 'success');
      
  this.ngOnInit();
}
upload(id, file: File): void {

    this.activityService.addImg(id,file).subscribe()
}




//enregister l'activiter
  goNext(){
    this.submittedAddAct=true;
    
  this.idUserConnected = this.tokenStorage.getUser().id;
    this.addActToken=this.stringGen(32);
    //localStorage.setItem('addActToken', this.addActToken);
    if (this.addFormActivity.invalid) {
      return;
    }
  
    let 
      data = {
        idClient : this.idUserConnected,
        name: this.addFormActivity.value.name,
        description: this.addFormActivity.value.description,
        type: this.addFormActivity.value.type,
        region: this.addFormActivity.value.region,
        prix: this.addFormActivity.value.prix,
        min_participants: this.addFormActivity.value.min_participants,
        max_participants:this.addFormActivity.value.max_participants,
        published:false,
        addActToken : this.addActToken,
        
      };
    
  console.log(data)
  
    this.activityService.addAct(data).subscribe((res) => {
        
      });
  
     
    this.next=true;
  }

  //get current activity 
  getCurrentAct(){
    this.save=true;
    this.activityService.getActByToken(this.addActToken).subscribe((res) => {
      this.currenAct=res;
      console.log('current t act',res);
    });
  }
  annulerAdd(){

  }


removeImageEdit(i, imagepath) {
  this.imageDeleteFrom.value.id = i;
  this.imageDeleteFrom.value.ImagePath = imagepath;
}

removeImage(i) {
  this.imageurls.splice(i, 1);
}
}
