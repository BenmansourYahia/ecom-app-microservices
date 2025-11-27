import { Component, OnInit } from '@angular/core';
import { ProductService, Product } from '../../services/product';

@Component({
  selector: 'app-products',
  standalone: false,
  templateUrl: './products.html',
  styleUrl: './products.css',
})
export class ProductsComponent implements OnInit {
  products: Product[] = [];
  loading = true;
  error = '';

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProducts().subscribe({
      next: (data) => {
        this.products = data._embedded?.products || [];
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Erreur lors du chargement des produits';
        this.loading = false;
        console.error(err);
      }
    });
  }
}
