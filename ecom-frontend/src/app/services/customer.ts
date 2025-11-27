import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Customer {
  id: number;
  name: string;
  email: string;
}

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiUrl = '/api/customer-service/api/customers';

  constructor(private http: HttpClient) {}

  getCustomers(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  getCustomer(id: number): Observable<Customer> {
    return this.http.get<Customer>(`${this.apiUrl}/${id}`);
  }
}
