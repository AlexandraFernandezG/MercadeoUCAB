import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { SolicitudEstudioComponent } from '../solicitud-estudio/solicitud-estudio.component';
import { EstudiosService } from '../../servicios/estudios.service';
import { Estudio } from '../../modelos/estudio';

@Component({
  selector: 'app-estudios-cliente',
  templateUrl: './estudios-cliente.component.html',
  styleUrls: ['./estudios-cliente.component.css']
})
export class EstudiosClienteComponent implements OnInit {

  estudios: Estudio[];
  constructor( private estudiosService: EstudiosService, private dialog: MatDialog ) { }

  ngOnInit(): void {
    this.estudiosService.getEstudios().subscribe(estudios => {
      this.estudios = estudios;
    });
  }
  onCreate(){
    this.dialog.open(SolicitudEstudioComponent);
  }
}
