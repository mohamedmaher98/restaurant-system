import {BaseEntity} from './BaseEntity';

export class Chef extends BaseEntity {
  specialty: string;
  facebookLink: string;
  twitterLink: string;
  instagramLink: string;


  constructor(chef: Partial<Chef>) {
    super(chef);
    if (chef) {
      this.specialty = chef.specialty;
      this.facebookLink = chef.facebookLink;
      this.twitterLink = chef.twitterLink;
      this.instagramLink = chef.instagramLink;
    }
  }
}
