import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router: Router, private auth: AuthService) {
  }

  performSearch(searchKey: string) {
    if (searchKey.trim() === '') {
      this.router.navigate(['/products']);
    } else {
      this.router.navigate(['/search', searchKey]);
    }
  }

  get getAuth() {
    return this.auth;
  }
}
