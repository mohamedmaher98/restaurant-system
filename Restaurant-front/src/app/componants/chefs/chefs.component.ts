import {Component, OnInit} from '@angular/core';
import {ChefService} from '../../../services/ChefService';
import {Chef} from '../../../model/Chef';

@Component({
  selector: 'app-chefs',
  templateUrl: './chefs.component.html',
  styleUrls: ['./chefs.component.css']
})
export class ChefsComponent implements OnInit {
  chefs: Chef[];

  constructor(private service: ChefService) {
  }

  ngOnInit(): void {
    this.getAllChefs();
  }

  getAllChefs() {
    const chefs = this.service.getAllChefs();
    chefs.subscribe(
      response => this.chefs = response
    );
  }
}
