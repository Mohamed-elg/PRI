import { Component, OnInit } from '@angular/core';
import { ShareDataService } from '../services/share-data-service.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class ApiPayload {
  constructor(
    public client: {
      "id": number,
      "nom": string,
      "ref": string,
      "contact": {
        "id": number,
        "nom": string,
        "tel": string,
        "mail": string
      }
    },
    public assemblage: {
      "id": number,
      "moteurs": {
        "marque": string,
        "numSerie": string,
        "typeMoteur": string,
        "id": number
      }[],
      "pompes": {
        "id": number,
        "marque": string,
        "numSerie": string
      }[],
      "reducteurs": {
        "id": number,
        "marque": string,
        "numSerie": string
      }[],
      "ventilateurs": {
        "id": number,
        "marque": string,
        "numSerie": string
      }[]
    }
  ) {}
}

@Component({
  selector: 'add-mechanical-sheet',
  templateUrl: './add-mechanical-sheet.component.html',
  styleUrls: ['./add-mechanical-sheet.component.css']
})
export class AddMechanicalSheetComponent implements OnInit{
  client!: any;
  mechanical_sheet_url = 'http://localhost:8081/api/FicheMecanique';

  constructor(private shareDataService: ShareDataService, private http: HttpClient) {}

  ngOnInit(): void {
    this.shareDataService.currentClientValue.subscribe(value => {
      this.client = value;
    });
      
  }
  
  onSubmit() {
    const payload = {
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
        "moteurs": [
          {
            "marque": "",
            "numSerie": "",
            "typeMoteur": "",
            "id": 0
          }
        ],
        "pompes": [
          {
            "id": 0,
            "marque": "",
            "numSerie": ""
          }
        ],
        "reducteurs": [
          {
            "id": 0,
            "marque": "",
            "numSerie": ""
          }
        ],
        "ventilateurs": [
          {
            "id": 0,
            "marque": "",
            "numSerie": ""
          }
        ]
      }
    }
  
    const payloadString = JSON.stringify(payload);
    console.log('Payload:', payloadString);

    const authToken = localStorage.getItem('authToken');

    if (!authToken) {
      console.error('No token found in localStorage');
      return;
    }

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${authToken}`,
      'Content-Type': 'application/json'
    });

    this.http.post(this.mechanical_sheet_url, payloadString, { headers })
      .subscribe(response => {
        console.log('Post request successful:', response);
      }, error => {
        console.error('Error in post request:', error);
      });
  }
}