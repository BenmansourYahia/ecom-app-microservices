import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Bill {
  id: number;
  billingDate: Date;
  customerId: number;
  customer?: any;
  productItems?: any[];
}

@Injectable({
  providedIn: 'root',
})
export class BillService {
  private apiUrl = '/api/billing-service/api/bills';

  constructor(private http: HttpClient) { }

  getBills(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }

  getBill(id: number): Observable<Bill> {
    return this.http.get<Bill>(`${this.apiUrl}/${id}`);
  }
}
