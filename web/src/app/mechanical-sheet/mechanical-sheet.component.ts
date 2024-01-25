import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Client } from '../client/client.component';

@Component({
  selector: 'mechanical-sheet',
  templateUrl: './mechanical-sheet.component.html',
  styleUrls: ['./mechanical-sheet.component.css']
})
export class MechanicalSheetComponent implements OnInit{
  sheetId!: string;
  action!: string;
  mechanical_sheet_url = 'http://localhost:8081/api/FicheMecanique?id=';
  mechanicalSheet: any;

  clientData: Client = new Client;
  equipments: any;

  constructor(private route: ActivatedRoute, private http: HttpClient) {}

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
      this.http.get(this.mechanical_sheet_url+this.sheetId, { headers }).subscribe( response => {
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
  }
}