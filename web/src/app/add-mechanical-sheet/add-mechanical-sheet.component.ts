import { Component, OnInit } from '@angular/core';
import { ShareDataService } from '../services/share-data-service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'add-mechanical-sheet',
  templateUrl: './add-mechanical-sheet.component.html',
  styleUrls: ['./add-mechanical-sheet.component.css'],
})
export class AddMechanicalSheetComponent implements OnInit {
  client!: any;
  equipments!: any;
  // mechanical_sheet_url = 'http://localhost:8081/api/FicheMecanique';
  mechanical_sheet_url = `${localStorage.getItem('URL_API')}/FicheMecanique`;

  errorStatus: boolean = false;
  submitStatus: boolean = false;

  constructor(
    private shareDataService: ShareDataService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.shareDataService.currentClientValue.subscribe((value) => {
      this.client = value;
    });
    this.shareDataService.currentEquipmentsValues.subscribe((value) => {
      this.equipments = value;
    });
  }

  onSubmit() {
    const payload = {
      "numeroDossier": this.client.number,
      "client": {
        "id": 0,
        "nom": this.client.client,
        "ref": this.client.ref,
        "contact": {
          "id": 0,
          "nom": this.client.name,
          "tel": this.client.phone,
          "mail": this.client.mail
        }
      },
      "assemblage": {
        "id": 0,
        "moteurs": [],
        "pompes": [],
        "reducteurs": [],
        "ventilateurs": []
      },
      "options": []
    }

    if (!!this.equipments) {
      this.equipments.forEach((equipment: any) => {
        const equipmentType = equipment.selectedEquipement.toLowerCase();
        const equipmentDetail = equipment.selectedDetail.toLowerCase();

        const newEquipment = {
          type: equipmentDetail,
        };

        (payload.assemblage as any)[equipmentType + 's'].push(newEquipment);
      });
    }

    const payloadString = JSON.stringify(payload);

    const authToken = localStorage.getItem('authToken');

    if (!authToken) {
      console.error('No token found in localStorage');
      return;
    }

    const headers = new HttpHeaders({
      Authorization: `Bearer ${authToken}`,
      'Content-Type': 'application/json',
    });

    this.http
      .post(this.mechanical_sheet_url, payloadString, { headers })
      .subscribe(
        (response) => {
          console.log('Post request successful:', response);
          setTimeout(() => {
            this.resetForm();
            this.submitStatus = true;
          }, 0);
        },
        (error) => {
          console.error('Error in post request:', error);
          this.errorStatus = true;
        }
      );
  }

  resetForm() {
    this.client = null;
    this.equipments = null;
    this.errorStatus = false;
  }
}
