import {Component, NgModule} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RegisterComponent } from "./user/register/register.component";
import { LoginComponent } from "./user/login/login.component";
import { AddItemComponent } from "./grocery-item/add-item/add-item.component";
import {ItemDetailsComponent} from "./grocery-item/item-details/item-details.component";
import {MainPageComponent} from "./main-page/main-page.component";
import {CreateGroceryListComponent} from "./grocery-list/create-grocery-list/create-grocery-list.component";
import {GroceryListComponent} from "./grocery-list/grocery-list.component";


const routes: Routes = [

  { path: '', redirectTo: 'employee', pathMatch: 'full' },

  { path: '', redirectTo: 'user', pathMatch: 'full' },
  { path: 'register', component: RegisterComponent },

  { path: 'login', component: LoginComponent },


  { path: 'mainPage', component: MainPageComponent},
  { path: 'mainPage/addGroceryList', component: CreateGroceryListComponent},
  { path: 'groceryList/:groceryListId', component: GroceryListComponent},
  { path: 'groceryList/:groceryListId/itemDetails/:itemId', component: ItemDetailsComponent},
  { path: 'groceryList/:groceryListId/addItem', component: AddItemComponent },
  { path: 'groceryListDetails/:groceryListId', component: GroceryListComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
