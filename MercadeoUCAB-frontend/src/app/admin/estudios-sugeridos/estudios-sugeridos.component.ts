import { Component, OnInit } from '@angular/core';
import { Estudio } from 'src/app/modelos/estudio';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { EstudiosComponent } from '../estudios/estudios.component';

@Component({
  selector: 'app-estudios-sugeridos',
  templateUrl: './estudios-sugeridos.component.html',
  styleUrls: ['./estudios-sugeridos.component.css']
})
export class EstudiosSugeridosComponent implements OnInit {

  estudios: Estudio[];
  constructor(
    private service: EstudiosService,
  ) { }

  ngOnInit(): void {
    this.service.getEstudios()
    .subscribe(data => {this.estudios = data;
    } );

  //  this.service.getEstudiosSugeridos()
  //  .subscribe(data => {this.estudios = data;
  //  } );
    
  }


}
