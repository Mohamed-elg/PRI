import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Client } from '../client/client.component';


@Injectable({
  providedIn: 'root'
})
export class ShareDataService {
  private defaultClient: Client = new Client();

  private clientValueSource = new BehaviorSubject<Client>(this.defaultClient);
  currentClientValue = this.clientValueSource.asObservable();

  private equipmentsValuesSource = new BehaviorSubject(null);
  currentEquipmentsValues = this.equipmentsValuesSource.asObservable();

  constructor() { }

  updateClientValue(value: Client) {
    this.clientValueSource.next(value);
  }

  getClientValue() {
    return this.clientValueSource.getValue();
  }

  updateEquipmentsValues(value: any) {
    this.equipmentsValuesSource.next(value);
  }

  getEquipmentsValues() {
    return this.equipmentsValuesSource.getValue();
  }
}
