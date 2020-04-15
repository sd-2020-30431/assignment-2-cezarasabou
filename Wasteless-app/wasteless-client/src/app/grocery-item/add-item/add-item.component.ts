import { Component, OnInit } from '@angular/core';
import { Item } from '../item';
import {ActivatedRoute, Router} from '@angular/router';
import { ItemService } from '../item.service';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  item: Item =  new Item();
  submitted = false;
  groceryListId: string;

  constructor(private itemService: ItemService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.groceryListId = this.activatedRoute.snapshot.url[1].path;
  }

  newItem(): void{
    this.submitted = false;
    this.item = new Item();
  }

   save() {
      this.itemService.createItem(this.groceryListId, this.item)
        .subscribe(
          data => {
            this.gotoList();
          },
            error => console.log(error));
    }

    onSubmit() {
      this.submitted = true;
      this.save();
    }

    gotoList() {
      this.router.navigate(['../groceryList',this.groceryListId]);
    }

  keyDownFunction(event) {
    if(event.keyCode == 13) {
      alert('you just clicked enter');
      // rest of your code
    }
  }
}
