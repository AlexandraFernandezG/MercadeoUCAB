import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Estudio, Estudio2 } from 'src/app/modelos/estudio';
import { EstudiosService } from 'src/app/servicios/estudios.service';

@Component({
  selector: 'app-dialog',
  templateUrl: './dialog.component.html',
  styleUrls: ['./dialog.component.css']
})
export class DialogComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Estudio2,
    private service: EstudiosService,
  
  ) { }
  
  estudio: Estudio [];
  ngOnInit(): void {
    console.log(this.data.id);
  }

}
