import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Solicitud2 } from 'src/app/modelos/solicitud';

@Component({
  selector: 'app-add-estudio',
  templateUrl: './add-estudio.component.html',
  styleUrls: ['./add-estudio.component.css']
})
export class AddEstudioComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Solicitud2,
  ) { }

  ngOnInit(): void {
    console.log(this.data.id);
  }

}
