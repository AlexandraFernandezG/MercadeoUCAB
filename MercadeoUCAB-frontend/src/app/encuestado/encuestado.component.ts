import { Component, OnInit } from '@angular/core';

declare var $;

@Component({
  selector: 'app-encuestado',
  templateUrl: './encuestado.component.html',
  styleUrls: ['./encuestado.component.css']
})
export class EncuestadoComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  action() {
    $('body').toggleClass('sidebar-toggled');
    $('.sidebar').toggleClass('toggled');
  }

}
