import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShareDataService {
  private clientValueSource = new BehaviorSubject<string>('');
  currentClientValue = this.clientValueSource.asObservable();

  constructor() { }

  updateClientValue(value: string) {
    this.clientValueSource.next(value);
  }
}
