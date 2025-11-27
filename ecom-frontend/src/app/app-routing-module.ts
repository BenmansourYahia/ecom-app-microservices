import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CustomersComponent } from './components/customers/customers';
import { ProductsComponent } from './components/products/products';
import { BillsComponent } from './components/bills/bills';

const routes: Routes = [
  { path: 'customers', component: CustomersComponent },
  { path: 'products', component: ProductsComponent },
  { path: 'bills', component: BillsComponent },
  { path: '', redirectTo: '/customers', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
