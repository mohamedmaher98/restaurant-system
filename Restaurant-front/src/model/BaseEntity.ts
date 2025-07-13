export abstract class BaseEntity {
  id: string;
  name: string;
  imagePath: string;

  constructor(entity?: Partial<BaseEntity>) {
    if (entity) {
      this.id = entity.id;
      this.name = entity.name;
      this.imagePath = entity.imagePath;
    }
  }
}
