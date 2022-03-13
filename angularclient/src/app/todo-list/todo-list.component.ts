import { Component, OnInit } from '@angular/core';
import {Todo} from "../todo";
import {TodoListService} from "../todo-list.service";

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent implements OnInit {

  todoList: Todo[] | undefined;

  constructor(private todoListService: TodoListService) {

  }

  ngOnInit(): void {
    this.todoListService.findAll().subscribe(data => {
      this.todoList = data;
    })
  }

}
