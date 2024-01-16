import { Component } from '@angular/core';

interface Container {
  showOptionInput: boolean;
  details: string[];
  equipements: string[];
  selectedEquipement: string;
  selectedDetail: string;
  visibleDetails: string[];
  newEquipment: string;
  newDetail: string;
}

@Component({
  selector: 'equipment',
  templateUrl: './equipment.component.html',
  styleUrls: ['./equipment.component.css']
})
export class EquipementComponent {
  containers: Container[] = [];

  constructor() {
    this.addContainer();
  }

  addContainer() {
    const newContainer: Container = {
      showOptionInput: false,
      details: ['Moteur synchrone', 'Moteur asynchrone', 'Moteur courant continu', 'Moteur SERVO', 'Pompe centrifuge', 'Pompe à vide'],
      equipements: ['Moteur', 'Réducteur', 'Pompe', 'Ventilateur', 'Soufflante'],
      selectedEquipement: '',
      selectedDetail: '',
      visibleDetails: [],
      newEquipment: '',
      newDetail: ''
    };

    this.containers.push(newContainer);
    this.selectContainer(newContainer);
  }

  selectContainer(container: Container) {
  }

  onEquipementSelect(container: Container, equipement: string) {
    container.selectedEquipement = equipement;
    this.updateVisibleDetails(container);
  }

  updateVisibleDetails(container: Container) {
    container.visibleDetails = [];
    if (container.selectedEquipement === 'Moteur') {
      container.visibleDetails.push('Moteur synchrone', 'Moteur asynchrone', 'Moteur courant continu', '+');
    } else if (container.selectedEquipement === 'Pompe') {
      container.visibleDetails.push('Pompe centrifuge', 'Pompe à vide', '+');
    } else {
      container.visibleDetails.push('+');
    }
  }

  onDetailSelect(container: Container, detail: string) {
    if (detail === '+') {
      container.showOptionInput = true;
      container.selectedDetail = container.newDetail;
    } else {
      container.selectedDetail = detail;
    }
  }

  onAddOptionClick() {
    this.addContainer();
  }

  isDetailVisible(container: Container, detail: string): boolean {
    return container.visibleDetails.includes(detail);
  }

}