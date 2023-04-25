import { Component, OnInit } from '@angular/core';
import {CategoryService} from "./category.service";
import {Category} from "./model/category";
import {HttpErrorResponse} from "@angular/common/http";
import { Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  // public categories: Category[] = [];
  public categories = new Array<Category>();

  public getCategory: Category | undefined;

  @Output() messageEvent = new EventEmitter<number>();

  constructor(private _categoryService: CategoryService) { }

  ngOnInit(): void {
    this.getCategoriesByHttp();
  }
  public getCategoriesByHttp(): void {
    this._categoryService.getCategoriesByHttp()
      .subscribe(data =>
      {this.categories = data;
        console.log(data);}
      );
  }

  public getCategoryById(categoryId: number): void {
    this._categoryService.getCategoryById(categoryId)
      .subscribe(data => {
          this.getCategory = data;
          console.log(data);},
        (error: HttpErrorResponse) => {alert(error.message)});
  }

  sendCategories(id: number) {
    this.messageEvent.emit(id);
  }



}
