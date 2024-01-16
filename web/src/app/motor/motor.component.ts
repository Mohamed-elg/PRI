import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'motor',
  templateUrl: './motor.component.html',
  styleUrls: ['./motor.component.css']
})
export class MotorComponent {
customValue: any;
onEquipmentTypeChange() {
throw new Error('Method not implemented.');
}
filterSpecificEquipments(arg0: any): any {
throw new Error('Method not implemented.');
}
filterEquipmentTypes(arg0: any): any {
throw new Error('Method not implemented.');
}

  @ViewChild('autreOptionInput') autreOptionInput!: ElementRef;

  showOptionInput: boolean = false;
  details: string[] = [
    'Moteur synchrone',
    'Moteur asynchrone',
    'Moteur courant continu',
    'Moteur SERVO',
    'Pompe centrifuge',
    'Pompe à vide'
  ];

  equipements: string[] = ['Moteur', 'Réducteur', 'Pompe', 'Ventilateur', 'Soufflante'];

  selectedEquipement: string = '';
  selectedDetail: string = '';
  visibleDetails: string[] = [];
  newEquipment: string = '';
  newDetail: string = '';

  onEquipementSelect(event: Event, equipement: string) {
    event.preventDefault();
    this.selectedEquipement = equipement;
    this.updateVisibleDetails();
  }

  updateVisibleDetails() {
    this.visibleDetails = [];
    if (this.selectedEquipement === 'Moteur') {
      this.visibleDetails.push('Moteur synchrone', 'Moteur asynchrone', 'Moteur courant continu', '+');
    } else if (this.selectedEquipement === 'Pompe') {
      this.visibleDetails.push('Pompe centrifuge', 'Pompe à vide', '+');
    }
    else
    this.visibleDetails.push( '+');
  }

  onDetailSelect(event: Event, detail: string) {
    event.preventDefault();
    if (detail === '+') {
      this.showOptionInput = true;
      this.selectedDetail = this.newDetail;
    } else {
      this.selectedDetail = detail;
    }
  }

  onAddOptionClick() {
    const autreOption = this.autreOptionInput.nativeElement.value;
    console.log('Autre option spécifiée :', autreOption);
    // Handle the logic for adding the custom option
  }

  onFormSubmit() {
    console.log('Form submitted');
    // Handle the logic for submitting the form
  }

  isDetailVisible(detail: string): boolean {
    return this.visibleDetails.includes(detail);
  }

  onAddEquipmentClick(event: Event) {
    event.preventDefault();
    if (this.newEquipment) {
      this.equipements.push(this.newEquipment);
      this.newEquipment = '';
    }
  }
}
