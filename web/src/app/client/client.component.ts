import { Component, OnInit } from '@angular/core';
import { ShareDataService } from '../services/share-data-service.service';
import { Input } from '@angular/core';

export class Client {
  constructor(
    public client: string = '',
    public number: string = '',
    public name: string = '',
    public phone: string = '',
    public date: string = '',
    public ref: string = '',
    public mail: string = ''
  ) {}
}

@Component({
  selector: 'client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.css']
})
export class ClientComponent implements OnInit{
  @Input() client: Client = new Client();
  @Input() disabled!: boolean;

  constructor(private sharedDataService: ShareDataService) { }

  ngOnInit(): void {
  }

  updateClientValue(){
    this.sharedDataService.updateClientValue(this.client);
  }
}
