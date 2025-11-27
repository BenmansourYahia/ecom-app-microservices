import { Component, OnInit } from '@angular/core';
import { BillService, Bill } from '../../services/bill';

@Component({
  selector: 'app-bills',
  standalone: false,
  templateUrl: './bills.html',
  styleUrl: './bills.css',
})
export class BillsComponent implements OnInit {
  bills: Bill[] = [];
  loading = true;
  error = '';

  constructor(private billService: BillService) { }

  ngOnInit(): void {
    this.loadBills();
  }

  loadBills(): void {
    this.billService.getBills().subscribe({
      next: (data) => {
        this.bills = data._embedded?.bills || [];
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des factures';
        this.loading = false;
        console.error(err);
      }
    });
  }
}
