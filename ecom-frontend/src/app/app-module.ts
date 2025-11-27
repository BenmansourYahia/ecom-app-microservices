import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { CustomersComponent } from './components/customers/customers';
import { ProductsComponent } from './components/products/products';
import { BillsComponent } from './components/bills/bills';

@NgModule({
  declarations: [
    App,
    CustomersComponent,
    ProductsComponent,
    BillsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(withInterceptorsFromDi())
  ],
  bootstrap: [App]
})
export class AppModule { }
