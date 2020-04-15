import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../user/user";
import {GroceryList} from "../grocery-list/groceryList";
import {WasteCalculator} from "./waste-calculator";

@Injectable({
  providedIn: 'root'
})
export class MainPageService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getActiveUser(): Observable<any> {
    return this.http.get(`${this.baseUrl}/activeUser`);
  }

  getAllGroceryLists(userId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${userId}/groceryLists`);
  }

  getGroceryList(userId:number, listId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${userId}/groceryList/${listId}`);
  }

  createGroceryList(groceryList: string, userId: number): Observable<any> {
    return this.http.post(`${this.baseUrl}/${userId}/createGroceryList`, groceryList);
  }

  updateItem(userId: number, groceryListId: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${userId}/updateGroceryListItem/${groceryListId}`, value);
  }

  deleteItem(userId: number,groceryListId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${userId}/deleteGroceryList/${groceryListId}`, { responseType: 'text' });
  }

  //aici trimit obiectul cu tot ce trebuie
  calculateWaste(userId: number, groceryListId:number, wasteCalculator: WasteCalculator): Observable<any>{
    return this.http.post(`${this.baseUrl}/${userId}/${groceryListId}`, wasteCalculator)
  }

  getWasteLevel(userId:number, groceryListId:number){
    return this.http.get(`${this.baseUrl}/${userId}/${groceryListId}`);
  }

  getTextFileReport(wasteCalculator: WasteCalculator){
    return this.http.post(`${this.baseUrl}/textFile`,wasteCalculator);
  }

  getJsonFileReport(wasteCalculator: WasteCalculator){
    return this.http.post(`${this.baseUrl}/jsonFile`,wasteCalculator);
  }
}
