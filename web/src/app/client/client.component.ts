import { Component, OnInit } from '@angular/core';
import { ShareDataService } from '../services/share-data-service.service';

@Component({
  selector: 'client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit{
  inputClient: string ='';

  constructor(private sharedDataService: ShareDataService) { }

  ngOnInit(): void {
    
  }

  updateClientValue(){
    this.sharedDataService.updateClientValue(this.inputClient);
  }
}
