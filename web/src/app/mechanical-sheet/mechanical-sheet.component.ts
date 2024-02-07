import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Client } from '../client/client.component';
import { ShareDataService } from '../services/share-data-service.service';

@Component({
  selector: 'mechanical-sheet',
  templateUrl: './mechanical-sheet.component.html',
  styleUrls: ['./mechanical-sheet.component.css']
})
export class MechanicalSheetComponent implements OnInit{
  sheetId!: string;
  action!: string;
  mechanicalSheet: any;
  clientData: Client = new Client;
  client!: any;
  equipments!: any;

  errorStatus: boolean = false;
  submitStatus: boolean = false;

  mechanicalSheetUrl = `${localStorage.getItem('URL_API')}/FicheMecanique`;
  get1MechanicalSheetUrl = this.mechanicalSheetUrl+'?id=';
  
  constructor(private route: ActivatedRoute, private http: HttpClient, private shareDataService: ShareDataService) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.action = params['action'];
      this.sheetId = params['id'];
    });

    const authToken = localStorage.getItem('authToken');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${authToken}`
    });

    if(this.action=='view'){
      this.http.get(this.get1MechanicalSheetUrl+this.sheetId, { headers }).subscribe( response => {
        this.mechanicalSheet = response;
        this.mechanicalSheet = this.mechanicalSheet[0];
  
        const client = this.mechanicalSheet.client;
  
        this.clientData.client = client?.nom || '';
        this.clientData.date = this.mechanicalSheet.dateCreation || '';
        this.clientData.mail = client?.contact.mail || '';
        this.clientData.name = client?.contact.nom || '';
        this.clientData.number = this.mechanicalSheet.numeroDossier || '';
        this.clientData.phone = client?.contact.tel || '';
        this.clientData.ref = client?.ref || '';
  
        this.equipments = this.mechanicalSheet.assemblage;
  
      }, error => {
        console.error('Error in get request:', error);
      })
    }
    else {
      this.shareDataService.currentClientValue.subscribe((value) => {
        this.client = value;
      });
      this.shareDataService.currentEquipmentsValues.subscribe((value) => {
        this.equipments = value;
      });
    }
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

    console.log(payload);

    if (!!this.equipments) {
      this.equipments.forEach((equipment: any) => {
        const equipmentType = removeAccents(equipment.selectedEquipement.toLowerCase());
        const equipmentDetail = equipment.selectedDetail.toLowerCase() || '';

        const newEquipment = {
          type: equipmentDetail,
        };

        (payload.assemblage as any)[equipmentType + 's'].push(newEquipment);

        console.log(payload);
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
      .post(this.mechanicalSheetUrl, payloadString, { headers })
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

function removeAccents(str: string): string {
  return str.normalize("NFD").replace(/[\u0300-\u036f]/g, "");
}