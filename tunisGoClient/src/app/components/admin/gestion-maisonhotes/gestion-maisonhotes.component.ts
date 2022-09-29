import { Component, OnInit ,TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-gestion-maisonhotes',
  templateUrl: './gestion-maisonhotes.component.html',
  styleUrls: ['./gestion-maisonhotes.component.css']
})
export class GestionMaisonhotesComponent implements OnInit {
  modalRef: BsModalRef;
  addFormHouse: FormGroup;
  updateFormHouse: FormGroup;
  constructor(private modalService: BsModalService,
    private formBuilder: FormBuilder,) {
      
     }
     ngOnInit(): void {
      this.addFormHouse = this.formBuilder.group({
        nom: [null, Validators.required],
        prenom: [null, [Validators.required]],
        numberMember: [null, [Validators.required]],
        num_tel: [null, [Validators.required]],
        email: [null, Validators.required],
        password: [null, Validators.required],
        
      })
    }

     get addHouseControls() {
      return this.addFormHouse.controls;
    }
    openModal(template: TemplateRef<any>) {
      this.modalRef = this.modalService.show(template);
    }
    resetForm() {
      this.addFormHouse.reset();
      this.modalRef.hide();
    }
  

}
