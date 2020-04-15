import { Component, OnInit } from '@angular/core';
import {Item} from '../item';
import { ItemService } from '../item.service';
import { GroceryListComponent } from '../../grocery-list/grocery-list.component';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-item-details',
  templateUrl: './item-details.component.html',
  styleUrls: ['./item-details.component.css']
})
export class ItemDetailsComponent implements OnInit {

   itemId: string;
   groceryListId: string;
   item: Item;

   constructor(private activatedRoute: ActivatedRoute,private router: Router,
     private itemService: ItemService) { }

   ngOnInit() {
     this.item = new Item();

     this.itemId = this.activatedRoute.snapshot.params['itemId'];
     this.groceryListId = this.activatedRoute.snapshot.params['groceryListId'];

     this.itemService.getItem(this.itemId, this.groceryListId)
       .subscribe(data => {
         console.log(data)
         this.item = data;
       }, error => console.log(error));
   }

   list(){
     this.router.navigate(['../..'], {relativeTo: this.activatedRoute});
   }
}
