import { Component, OnInit ,TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import Swal from 'sweetalert2';
import {ActivityService} from 'src/app/services/activityService/activity.service';
import { Activity} from '../../../models/activity';
import { TokenStorageService } from '../../../services/tokenService/token-storage.service';

@Component({
  selector: 'app-gestion-activity',
  templateUrl: './gestion-activity.component.html',
  styleUrls: ['./gestion-activity.component.css']
})
export class GestionActivityComponent implements OnInit {
  activities : Activity[];
  currenAct : Activity;
  modalRef: BsModalRef;
  submittedAddAct = false;
  submittedUpdateAct = false;
  addFormActivity: FormGroup;
  updateFormActivity: FormGroup;
  listActs: any;
  idUserConnected:any;

  constructor(private modalService: BsModalService,
    private formBuilder: FormBuilder,
    private activityService : ActivityService,
    private tokenStorage:TokenStorageService) {
      
     }
     ngOnInit(): void {
      this.getAllAct();

      this.addFormActivity = this.formBuilder.group({
        name: [null, Validators.required],
        description: [null, [Validators.required]],
        type: [null, [Validators.required]],
        region: [null, [Validators.required]],
        prix: [null, [
          Validators.required,
          Validators.pattern(/^[0-9]\d*$/),
          ]],
        images: [null, Validators.required],
        
      }),
      this.addFormActivity = this.formBuilder.group({
        name: [null, Validators.required],
        description: [null, [Validators.required]],
        type: [null, [Validators.required]],
        region: [null, [Validators.required]],
        prix: [null, [
          Validators.required,
          Validators.pattern(/^[0-9]\d*$/),
          ]],
        images: [null, Validators.required],
        
      });
      this.updateFormActivity = this.formBuilder.group({
        name: [null, Validators.required],
        description: [null, [Validators.required]],
        type: [null, [Validators.required]],
        region: [null, [Validators.required]],
        prix: [null, [
          Validators.required,
          Validators.pattern(/^[0-9]\d*$/),
          ]],
        images: [null, Validators.required],
        
      })
    }

     get addActivityControls() {
      return this.addFormActivity.controls;
    }
    openModal(template: TemplateRef<any>) {
      this.modalRef = this.modalService.show(template);
    }
    resetAddFormAct() {
      this.submittedAddAct=false;
      this.addFormActivity.reset();
      this.modalRef.hide();
    }
    get updateUserControls() {
      return this.updateFormActivity.controls;
    }
    resetUpdateFormAct() {
      this.submittedUpdateAct=false;
      this.updateFormActivity.reset();
      this.modalRef.hide();
    }
//get all activities
     getAllAct(): void {
    this.activityService.getAll()
      .subscribe(
        data => {
          this.listActs = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

//get activity by id

getActivityById(id) {
  this.activityService.getAct(id).subscribe((res: Activity) => {
    this.currenAct = res;
    console.log(this.currenAct);
    this.updateFormActivity.setValue({
      name: this.currenAct.name,
      description: this.currenAct.description,
      type: this.currenAct.type,
      region: this.currenAct.region,
      prix: this.currenAct.prix,
      images: this.currenAct.images,
    });
  });
}
  //Create new activity
addNewActivity() {
  this.idUserConnected = this.tokenStorage.getUser().id;
  this.submittedAddAct = true;
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
      published:false,
      images: [this.addFormActivity.value.images]
      
    };
  
console.log(data)

  this.activityService.addAct(data).subscribe((res) => {
    
      Swal.fire('Activité ajouté avec succès!', '', 'success');
      this.ngOnInit();
      this.addFormActivity.reset();
      this.modalRef.hide();
    });
}

//update Activity
editActivity() {
  this.submittedUpdateAct=true;
  if (this.updateFormActivity.invalid) {
    return;
  }
  let 
    data = {
      idClient : this.idUserConnected,
      name: this.updateFormActivity.value.name,
      description: this.updateFormActivity.value.description,
      type: this.updateFormActivity.value.type,
      region: this.updateFormActivity.value.region,
      prix: this.updateFormActivity.value.prix,
      published:false,
      images: [this.updateFormActivity.value.images]
    };

  this.activityService
    .updateAct(this.currenAct.id, data)
    .subscribe(
      (response) => {
        console.log(data);
        console.log(response);
        Swal.fire('Cet activité a été modifiée avec succés', '', 'success');
        this.modalRef.hide();
        this.ngOnInit();
      },
      (error) => {
        console.log(error);
      }
      
    );
    
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
        this.activities = res;
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
