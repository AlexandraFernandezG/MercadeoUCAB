import { Component, OnInit, Input, Inject } from '@angular/core';
import { CategoriasService } from 'src/app/servicios/categorias.service';
import { MatDialog, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { Pregunta, Pregunta2 } from 'src/app/modelos/pregunta';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { DialogComponent } from '../dialog/dialog.component';

@Component({
  selector: 'app-preguntas-estudio',
  templateUrl: './preguntas-estudio.component.html',
  styleUrls: ['./preguntas-estudio.component.css']
})
export class PreguntasEstudioComponent implements OnInit {


  constructor(
    private service: PreguntasService,
    public actRoute: ActivatedRoute,
    public dialog: MatDialog,
    private router: Router,
    private formBuilder: FormBuilder,
    private location: Location
  ) { }

  ngOnInit(): void {
    
  }

  openModal(){
    this.dialog.open(DialogComponent);
  }

}

