import { Component, OnInit , TemplateRef} from '@angular/core';
import { UserService } from 'src/app/services/userService/user.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

import {User} from '../../../models/user';

@Component({
  selector: 'app-gestion-users',
  templateUrl: './gestion-users.component.html',
  styleUrls: ['./gestion-users.component.css']
})
export class GestionUsersComponent implements OnInit {
  users : User[];
  currentUser : User;
  listUsers: any;
  //currentUser = null;
  submittedAddUser = false;
  submittedUpdate = false;
  closeResult = '';
  modalRef: BsModalRef;
  addFormUser: FormGroup;
  updateFormUser: FormGroup;
  registerForm: any = {};
  constructor(private userService: UserService,
    private modalService: BsModalService,
    private formBuilder: FormBuilder, ) { }
  
  ngOnInit(): void {
    this.retrieveUsers();
    this.addFormUser = this.formBuilder.group({
      nom: [null, Validators.required],
      prenom: [null, [Validators.required]],
      phone: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        Validators.minLength(8),]],
      email: [null,
         [Validators.required,
          Validators.pattern(/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/)]],
      password: [null, [Validators.required, Validators.minLength(8)]],
      role: [null, Validators.required],
    }),
    this.updateFormUser = this.formBuilder.group({
      nom: [null, Validators.required],
      prenom: [null, [Validators.required]],
      phone: [null, [
        Validators.required,
        Validators.pattern(/^[0-9]\d*$/),
        Validators.minLength(8),]],
      email: [null, Validators.required],
    })
   
  }
  get addUserControls() {
    return this.addFormUser.controls;
  }
  resetAddForm() {
    this.submittedAddUser=false;
    this.addFormUser.reset();
    this.modalRef.hide();
  }
  get updateUserControls() {
    return this.updateFormUser.controls;
  }
  resetUpdateForm() {
    this.submittedUpdate=false;
    this.updateFormUser.reset();
    this.modalRef.hide();
  }

 //get all users
  retrieveUsers(): void {
    this.userService.getAll()
      .subscribe(
        data => {
          this.listUsers = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

//Create new user
addNewUser() {
  this.submittedAddUser = true;
  if (this.addFormUser.invalid) {
    return;
  }

  let 
    data = {
      nom: this.addFormUser.value.nom,
      prenom: this.addFormUser.value.prenom,
      email: this.addFormUser.value.email,
      numTel: this.addFormUser.value.phone,
      mdp: this.addFormUser.value.password,
      role: [this.addFormUser.value.role]
      
    };
  
console.log(data)

  this.userService.addUser(data).subscribe((res) => {
    if (res['code'] == 400) {
      Swal.fire({
        icon: 'error',
        title: 'oops...',
        text: 'This email already exist !',
      });
    } else {
      Swal.fire('User added succesufully!', '', 'success');
      
      }
      this.ngOnInit();
      this.addFormUser.reset();
      this.modalRef.hide();
    });

 
}
 
//get user by id
getUserById(id) {
  this.userService.getUser(id).subscribe((res: User) => {
    this.currentUser = res;
    console.log(this.currentUser);
    this.updateFormUser.setValue({
      nom: this.currentUser.nom,
      prenom: this.currentUser.prenom,
      phone: this.currentUser.numTel,
      email: this.currentUser.email,
      //password: this.currentUser.mdp,
    });
  });
}

//update user
editUser() {
  this.submittedUpdate=true;
  if (this.updateFormUser.invalid) {
    return;
  }
  let 
    data = {
      nom: this.updateFormUser.value.nom,
      prenom: this.updateFormUser.value.prenom,
      email: this.updateFormUser.value.email,
      numTel: this.updateFormUser.value.phone,
      //mdp: this.updateFormUser.value.password,
    };

  this.userService
    .updateUser(this.currentUser.id, data)
    .subscribe(
      (response) => {
        console.log(data);
        console.log(response);
        Swal.fire('This user was updated successufully !', '', 'success');
        this.modalRef.hide();
        this.ngOnInit();
      },
      (error) => {
        console.log(error);
      }
      
    );
    
}


//delete user
deleteUser(id) {
  Swal.fire({
    title: 'Are you sure?',
    text: 'You cannot take this back!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#3085d6',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Yes,delete it',
    cancelButtonText: 'Cancel',
  }).then((result) => {
    if (result.value) {
      this.userService.deleteUser(id).subscribe((res: any) => {
        this.users = res;
        this.ngOnInit();
      });
      Swal.fire(
        'Deleted',
        'User deleted successufully',
        'success'
      );
    }
  });
}


  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template);
  }

}