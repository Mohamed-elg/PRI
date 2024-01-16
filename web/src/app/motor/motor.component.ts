import { Component } from '@angular/core';

@Component({
  selector: 'motor',
  templateUrl: './motor.component.html',
  styleUrls: ['./motor.component.css']
})
export class MotorComponent {
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

  onEquipementSelect(equipement: string) {
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
    this.visibleDetails.push('+');
  }

  onDetailSelect(detail: string) {
    if (detail === '+') {
      this.showOptionInput = true;
      this.selectedDetail = this.newDetail;
    } else {
      this.selectedDetail = detail;
    }
  }

  onAddOptionClick() {
    console.log('Autre option spécifiée :');
  }

  isDetailVisible(detail: string): boolean {
    return this.visibleDetails.includes(detail);
  }

}
