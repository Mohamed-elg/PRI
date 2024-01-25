import { Component, Input } from '@angular/core';
import { ShareDataService } from '../services/share-data-service.service';

interface Container {
  showOptionInput: boolean;
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
  @Input() action!: string;
  @Input() equipments!: any;

  containers: Container[] = [];

  constructor(private shareDataService: ShareDataService) {
    this.addContainer();
  }

  addContainer() {
    const newContainer: Container = {
      showOptionInput: false,
      equipements: ['Moteur', 'Réducteur', 'Pompe', 'Ventilateur'],
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
      container.visibleDetails.push('Synchrone', 'Asynchrone', 'Courant continu','SERVO', '+');
    } else if (container.selectedEquipement === 'Pompe') {
      container.visibleDetails.push('Centrifuge', 'A vide', '+');
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

  updateEquipmentsValues() {
    const selectedValues = this.containers.map(container => ({
      selectedEquipement: container.selectedEquipement,
      selectedDetail: container.selectedDetail || container.newDetail || '',
    }));

    this.shareDataService.updateEquipmentsValues(selectedValues);
  }
}