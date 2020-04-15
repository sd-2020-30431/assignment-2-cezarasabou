import {Component, OnInit} from '@angular/core';
import {GroceryList} from "../groceryList";
import {MainPageService} from "../../main-page/main-page.service";
import {ActivatedRoute, Router} from "@angular/router";
import {User} from "../../user/user";

@Component({
  selector: 'app-create-grocery-list',
  templateUrl: './create-grocery-list.component.html',
  styleUrls: ['./create-grocery-list.component.css']
})
export class CreateGroceryListComponent implements OnInit {

  groceryList: GroceryList = new GroceryList();
  submitted = false;
  user: User;

  constructor(private mainPageService: MainPageService,
              private router: Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.mainPageService.getActiveUser().subscribe(
      (user) => this.user = user);
  }

  newGroceryList() {
    this.submitted = false;
    this.groceryList = new GroceryList();
  }

  save() {
    this.mainPageService.createGroceryList(this.groceryList.groceryListName, this.user.id)
      .subscribe(
        (createdGroceryList: GroceryList) => {
          console.log(createdGroceryList);
          this.goToGroceryList(createdGroceryList.groceryListId);
        } ,
        error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  goToGroceryList(groceryListId: number) {
    this.router.navigate(['groceryList', groceryListId], {relativeTo: this.activatedRoute.parent});
  }

  mainPage(){
    this.router.navigate(['mainPage']);
  }
}
