import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'list-mechanical-sheets',
  templateUrl: './list-mechanical-sheets.component.html',
  styleUrls: ['./list-mechanical-sheets.component.css']
})
export class ListMechanicalSheetsComponent implements OnInit{

  mechanical_sheet_url = 'http://localhost:8081/api/FicheMecanique';

  list_sheets: any;  

  constructor(private http: HttpClient) {}

  ngOnInit(): void {
    const authToken = localStorage.getItem('authToken');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${authToken}`
    });

      this.http.get(this.mechanical_sheet_url, { headers }).subscribe( response => {
        this.list_sheets = response;
      }, error => {
        console.error('Error in get request:', error);
      })
  }
}