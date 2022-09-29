import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import { TokenStorageService } from '../../../services/tokenService/token-storage.service';
import { UserService } from 'src/app/services/userService/user.service';
import {ActivityService} from 'src/app/services/activityService/activity.service';
import {User} from '../../../models/user';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
submittedUpdate = false;
  currentUser : User;
  id:any;
  profileFormUser: FormGroup;
  role:any;
  ListActivity:any;
  exist=false;

  constructor(private userService: UserService,
    private tokenStorage:TokenStorageService,
    private activityService : ActivityService,
    private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.getUserById();
    this.getListAct();
    this.profileFormUser = this.formBuilder.group({
      nomProfile: [null, Validators.required],
      prenomProfile: [null, [Validators.required]],
      phoneProfile: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        Validators.minLength(8),]],
      emailProfile: [null, Validators.required],
      
    });
    
    this.role = this.tokenStorage.getUser().roles;
  }

  get profileUserControls() {
    return this.profileFormUser.controls;
  }


  //get user by id
getUserById() {
  this.id = this.tokenStorage.getUser().id;
  this.userService.getUser(this.id).subscribe((res: User) => {
    this.exist=true;
    this.currentUser = res;
    console.log(this.currentUser);
    this.profileFormUser.setValue({
      nomProfile: this.currentUser.nom,
      prenomProfile: this.currentUser.prenom,
      phoneProfile: this.currentUser.numTel,
      emailProfile: this.currentUser.email,
    });
  });
}
//update profile
editProfileUser(){
  this.submittedUpdate=true;
  if (this.profileFormUser.invalid) {
    return;
  }
  let 
    data = {
      nom: this.profileFormUser.value.nomProfile,
      prenom: this.profileFormUser.value.prenomProfile,
      email: this.profileFormUser.value.emailProfile,
      numTel: this.profileFormUser.value.phoneProfile,
      //mdp: this.updateFormUser.value.password,
    };

  this.userService
    .updateUser(this.currentUser.id, data)
    .subscribe(
      (response) => {
        console.log(data);
        console.log(response);
        this.ngOnInit();
      },
      (error) => {
        console.log(error);
      }
      
    );
}
// get list activity
getListAct(){
  this.id = this.tokenStorage.getUser().id;
this.activityService.getActPerUser(this.id).subscribe((res: User) => {
  this.ListActivity = res;
  console.log("liste act",res);
});
}

//delete Act
deleteAct(id) {
  Swal.fire({
    title: 'êtes-vous sûr?',
    text: 'Vous ne pourrez plus récuperer cela!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Oui, supprimez-la!',
    cancelButtonText: 'Annuler',
  }).then((result) => {
    if (result.value) {
      this.activityService.deleteAct(id).subscribe((res: any) => {
        this.ListActivity = res;
        this.ngOnInit();
      });
      Swal.fire(
        'Supprimé',
        'Cet activité a été supprimée avec succés',
        'success'
      );
    }
  });
}


}