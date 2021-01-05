import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Solicitud } from 'src/app/modelos/solicitud';
import { EstudiosService } from 'src/app/servicios/estudios.service';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';
import { AddEstudioComponent } from '../estudios/add-estudio/add-estudio.component';

@Component({
  selector: 'app-solicitudes',
  templateUrl: './solicitudes.component.html',
  styleUrls: ['./solicitudes.component.css']
})
export class SolicitudesComponent implements OnInit {

  solicitudes: Solicitud[];
  id: number;
  constructor(
    private service: SolicitudEstudiosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog
  ) { }

  ngOnInit(): void {
    this.id= +this.actRoute.snapshot.paramMap.get("id");
    this.service.getSolicitudes()
    .subscribe(data => {this.solicitudes = data;
    } );
  }

  openModal(id: number):void{
    this.dialog.open(AddEstudioComponent,
      {
        data: {id: id}
      }
      );
  }

}
