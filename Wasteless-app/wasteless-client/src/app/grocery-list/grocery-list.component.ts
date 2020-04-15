import { Component, OnInit } from '@angular/core';
import { Observable } from "rxjs";
import { ItemService } from "../grocery-item/item.service";
import { Item } from "../grocery-item/item";
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-item-list',
  templateUrl: './grocery-list.component.html',
  styleUrls: ['./grocery-list.component.css']
})
export class GroceryListComponent implements OnInit {

  items: Observable<Item[]>;
  groceryListId: string;
  constructor(private itemService: ItemService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.groceryListId = this.activatedRoute.snapshot.url[1].path;
    this.reloadData();
  }

  reloadData(){
    this.items = this.itemService.getItemList(this.groceryListId, );
  }

  deleteItem(id:number){
    this.itemService.deleteItem(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }


  itemDetails(itemId:number){
    this.router.navigate(['itemDetails',itemId], {relativeTo: this.activatedRoute});
  }

  mainPage(){
    this.router.navigate(['../../mainPage'], {relativeTo: this.activatedRoute})
  }
}
