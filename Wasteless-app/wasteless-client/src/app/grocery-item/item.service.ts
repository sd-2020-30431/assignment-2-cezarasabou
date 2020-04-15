import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ItemService {

   private baseUrl = 'http://localhost:8080';

    constructor(private http: HttpClient) { }


    getItem(itemId: string, groceryListId: string): Observable<any> {
      return this.http.get(`${this.baseUrl}/${groceryListId}/${itemId}`);
    }

    createItem(groceryListId: string, item: Object): Observable<Object> {
      return this.http.post(`${this.baseUrl}/${groceryListId}/createItem`, item);
    }

    updateItem(itemId: number, value: any): Observable<Object> {
      return this.http.put(`${this.baseUrl}/${itemId}`, value);
    }

    deleteItem(itemId: number): Observable<any> {
      return this.http.get(`${this.baseUrl}/deleteItem/${itemId}`, { responseType: 'text' });
    }

  ///{groceryListId}/items/{id}
    getItemList(groceryListId: string): Observable<any> {
      return this.http.get(`${this.baseUrl}/${groceryListId}/items`);
    }
}
