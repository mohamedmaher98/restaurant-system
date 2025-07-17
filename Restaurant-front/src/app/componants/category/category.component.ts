import {Component, OnInit} from '@angular/core';
import {CategoryService} from '../../../services/category.service';
import {Category} from '../../../model/Category';
import {Router} from '@angular/router';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categories: Category[] = [];

  constructor(private categoryService: CategoryService, private router: Router) {

  }

  ngOnInit(): void {
    this.getAllCategories();
  }

  getAllCategories() {
    const categories = this.categoryService.getCategories();
    categories.subscribe(
      response => this.categories = response
    );
  }

  isActiveCategory(categoryName: string): boolean {
    if (categoryName === 'ALL') {
      return this.router.isActive(`/products`, true);
    }
    return false;
  }
}
