import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../services/category.service';
import {Category} from '../../../model/Category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoryService) {

  }

  ngOnInit(): void {
    this.getAllCategories();
  }

  // tslint:disable-next-line:typedef
  getAllCategories() {
    const categories = this.categoryService.getCategories();
    categories.subscribe(
      response => this.categories = response
    );
  }
}
