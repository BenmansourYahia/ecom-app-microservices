import { NgModule, provideBrowserGlobalErrorListeners } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';

import { AppRoutingModule } from './app-routing-module';
import { App } from './app';
import { CustomersComponent } from './components/customers/customers';
import { ProductsComponent } from './components/products/products';
import { BillsComponent } from './components/bills/bills';
import { ChatbotComponent } from './components/chatbot/chatbot';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    App,
    CustomersComponent,
    ProductsComponent,
    BillsComponent,
    ChatbotComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideHttpClient(withInterceptorsFromDi())
  ],
  bootstrap: [App]
})
export class AppModule { }
