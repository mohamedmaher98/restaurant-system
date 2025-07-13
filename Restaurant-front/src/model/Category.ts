import {BaseEntity} from './BaseEntity';

export class Category extends BaseEntity {
  flag: string;

  constructor(category?: Partial<Category>) {
    super(category);
    if (category) {
      this.flag = category.flag;
    }
  }
}
