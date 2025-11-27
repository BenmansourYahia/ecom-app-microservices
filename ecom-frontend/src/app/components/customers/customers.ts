import { Component, OnInit } from '@angular/core';
import { CustomerService, Customer } from '../../services/customer';

@Component({
  selector: 'app-customers',
  standalone: false,
  templateUrl: './customers.html',
  styleUrl: './customers.css',
})
export class CustomersComponent implements OnInit {
  customers: Customer[] = [];
  loading = true;
  error = '';

  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.loadCustomers();
  }

  loadCustomers(): void {
    this.customerService.getCustomers().subscribe({
      next: (data) => {
        this.customers = data._embedded?.customers || [];
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des clients';
        this.loading = false;
        console.error(err);
      }
    });
  }
}
