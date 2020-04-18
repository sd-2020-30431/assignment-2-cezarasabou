import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './user/login/login.component';
import { ItemDetailsComponent } from './grocery-item/item-details/item-details.component';
import { AddItemComponent } from './grocery-item/add-item/add-item.component';
import { RegisterComponent } from './user/register/register.component';
import { GroceryListComponent } from './grocery-list/grocery-list.component';
import { MainPageComponent } from './main-page/main-page.component';
import { CreateGroceryListComponent } from './grocery-list/create-grocery-list/create-grocery-list.component';
import {InjectableRxStompConfig, RxStompService, rxStompServiceFactory} from '@stomp/ng2-stompjs';
import {myRxStompConfig} from './my-rx-stomp.config';
import { MessagesComponent } from './messages/messages.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ItemDetailsComponent,
    AddItemComponent,
    RegisterComponent,
    GroceryListComponent,
    MainPageComponent,
    CreateGroceryListComponent,
    MessagesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: InjectableRxStompConfig,
      useValue: myRxStompConfig
    },
    {
      provide: RxStompService,
      useFactory: rxStompServiceFactory,
      deps: [InjectableRxStompConfig]
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
