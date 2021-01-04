import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { Solicitud } from 'src/app/modelos/solicitud';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';

@Component({
  selector: 'app-listarsolicitudes',
  templateUrl: './listarsolicitudes.component.html',
  styleUrls: ['./listarsolicitudes.component.css']
})
export class ListarsolicitudesComponent implements OnInit {

  solicitudes: Solicitud[];

  constructor(
    private service: SolicitudEstudiosService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.service.getSolicitudes()
    .subscribe(data => {this.solicitudes = data;
    } );
  }

}
