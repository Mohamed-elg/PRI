import { Component, OnInit } from '@angular/core';
import { ShareDataService } from '../services/share-data-service.service';

@Component({
  selector: 'add-mechanical-sheet',
  templateUrl: './add-mechanical-sheet.component.html',
  styleUrls: ['./add-mechanical-sheet.component.css']
})
export class AddMechanicalSheetComponent implements OnInit{
  clientValue!: string;

  constructor(private shareDataService: ShareDataService) {}

  ngOnInit(): void {
    this.shareDataService.currentClientValue.subscribe(value => {
      this.clientValue = value;
    });
      
  }
  
  onSubmit() {
    console.log(this.clientValue);
  }

}
