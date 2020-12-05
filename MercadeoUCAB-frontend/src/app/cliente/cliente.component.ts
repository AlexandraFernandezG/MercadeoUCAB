import { Component, OnInit } from '@angular/core';

declare var $;

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  action() {
    $('body').toggleClass('sidebar-toggled');
    $('.sidebar').toggleClass('toggled');
  }
}
