import {Injectable} from '@angular/core';
import {BackendService} from './backend.service';
import {Subscription} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loggedIn: boolean;

  constructor(private backendService: BackendService, private router: Router) {
  }

  public login(username: string, password: string): Subscription {
    return this.backendService
      .login(username, password)
      .subscribe(
        () => {
          this.saveToken(btoa(username + ':' + password));
          this.loggedIn = true;
        },
        () => {
          this.loggedIn = false;
          this.router.navigateByUrl('/login');
        });
  }

  public updateLoggedIn(): void {
    const token = this.getToken();
    if (token) {
      const decoded = atob(token);
      const parts = decoded.split(':');
      this.login(parts[0], parts[1]);
    } else {
      this.loggedIn = false;
      this.router.navigateByUrl('/login');
    }
  }

  public logout(): void {
    this.removeToken();
    this.loggedIn = false;
    this.router.navigateByUrl('/login');
  }

  public isLoggedIn(): boolean {
    return this.loggedIn;
  }

  public getToken(): string {
    return sessionStorage.getItem('token');
  }

  private saveToken(token: string): void {
    sessionStorage.setItem('token', token);
  }

  private removeToken(): void {
    sessionStorage.removeItem('token');
  }
}
