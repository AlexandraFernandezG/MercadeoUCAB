import { Component, OnInit } from '@angular/core';
import { EstudiosService } from '../../servicios/estudios.service';
import { Estudio } from '../../modelos/estudio';

@Component({
  selector: 'app-estudios-encuestado',
  templateUrl: './estudios-encuestado.component.html',
  styleUrls: ['./estudios-encuestado.component.css']
})
export class EstudiosEncuestadoComponent implements OnInit {
  
  estudios: Estudio[];

  constructor( private estudiosService: EstudiosService ) { }

  ngOnInit(): void {
    this.estudiosService.getEstudios().subscribe(estudios => {
      this.estudios = estudios;
    });
    
  }

}
