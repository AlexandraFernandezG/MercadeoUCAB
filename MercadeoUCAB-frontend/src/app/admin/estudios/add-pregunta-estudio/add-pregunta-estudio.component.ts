import { Component, Inject, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Estudio2 } from 'src/app/modelos/estudio';
import { Pregunta2, Pregunta3 } from 'src/app/modelos/pregunta';
import { Subcategoria } from 'src/app/modelos/subcategoria';
import { Usuario } from 'src/app/modelos/usuario';
import { PreguntasService } from 'src/app/servicios/preguntas.service';
import { PreguntasEstudioService } from 'src/app/servicios/preguntasestudios.service';
import { SolicitudEstudiosService } from 'src/app/servicios/solicitud-estudios.service';
import { SubcategoriasService } from 'src/app/servicios/subcategorias.service';
import { PreguntaComponent } from '../../pregunta/pregunta.component';

@Component({
  selector: 'app-add-pregunta-estudio',
  templateUrl: './add-pregunta-estudio.component.html',
  styleUrls: ['./add-pregunta-estudio.component.css']
})
export class AddPreguntaEstudioComponent implements OnInit {
  
  preguntaEst: Pregunta3;
  preguntasAgregadas: Pregunta3[];
  constructor(
    private fb: FormBuilder,
    private service: PreguntasService,
    private service2: PreguntasEstudioService,
    private serviceSubcategoria: SubcategoriasService,
    private preguntasService: PreguntasService,
    private SolicitudService: SolicitudEstudiosService,
    public actRoute: ActivatedRoute,
    public dialogRef: MatDialogRef<PreguntaComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Estudio2,
  ) { }

  preguntaForm = this.fb.group({
    descripcion: new FormControl( '', [ Validators.required, Validators.maxLength(200)]),
    tipoPregunta: new FormControl('', [Validators.required]),
    respuestas: this.fb.array([
      this.addRespuesta()
     ])
  });

  subcategorias: Subcategoria[] = [
  ];
  analistas: Usuario[];
  preguntas: any;
  pregunta: Pregunta2 = {
    id: 0,
    estatus: '',
    descripcion: '',
    tipoPregunta: '',
    subcategoriaDto: 0,
    usuarioDto: 0
  };

  ngOnInit(): void {
    //this.dialogRef.updateSize('600px', '600px');
    this.service.getPreguntas()
    .subscribe(data => {this.preguntas = data;
    } );
    console.log(this.data)
    // this.serviceSubcategoria.getSubcategorias()
    // .subscribe(catego => {this.subcategorias = catego;
    // } );
  }

  addRespuesta(){
    return this.fb.group({
      id: 1,
      nombre: [''],
      estatus: 'Activo',
      preguntaEncuestaDto: 1
    });
  }

  addResp(){
    (this.preguntaForm.controls.respuestas as FormArray).push(this.addRespuesta());
  }
  
  deleteRespuesta(indice: number){
    (this.preguntaForm.controls.respuestas as FormArray).removeAt(indice);
  }

  close(){
    this.dialogRef.close();
  }

  addPreguntaEstudio(): void{
    this.SolicitudService.getSolicitud(this.data.id).subscribe(
      data => {localStorage.setItem('idSubCat', JSON.stringify(data._producto._id))}
    )
    // let id = 1;
    // const estatus = 'Activo';
    // const usuarioDto = 1;
    // const respuestas = this.preguntaForm.get('respuestas').value;
    // this.service.createPreguntaEstudio({
    // id,
    // descripcion: this.preguntaForm.get('descripcion').value,
    // tipoPregunta: this.preguntaForm.get('tipoPregunta').value,
    // estatus,
    // usuarioDto,
    // subcategoriaDto: this.preguntaForm.get('subcategoriaDto').value
    // } as Pregunta2, this.data.id).subscribe(

    //   response => {
    //   if (this.preguntaForm.get('tipoPregunta').value == 'Selección Simple' || this.preguntaForm.get('tipoPregunta').value == 'Selección Múltiple'){
    //     this.service.createPreguntaRespuesta(response.id,respuestas).subscribe(
    //       respuesta => {
    //         console.log(respuesta);
    //       }
    //     );
    //  }
    //   }
    // );
    // this.dialogRef.close();
    let id = 1;
    const estatus = 'Activo';
    const usuarioDto = 1;
    const respuestas = this.preguntaForm.get('respuestas').value;
    this.service.createPregunta({
    id,
    descripcion: this.preguntaForm.get('descripcion').value,
    tipoPregunta: this.preguntaForm.get('tipoPregunta').value,
    estatus,
    usuarioDto,
    subcategoriaDto: JSON.parse(localStorage.getItem('idSubCat')),
    } as Pregunta2).subscribe(

      response => {
      console.log(response);
      console.log(respuestas);
      if (this.preguntaForm.get('tipoPregunta').value == 'Selección Simple' || this.preguntaForm.get('tipoPregunta').value == 'Selección Múltiple'){
        this.service.createPreguntaRespuesta(response.id,respuestas).subscribe(
          respuesta => {
            console.log(respuesta);
          }
        );
     }
      }
    );
    
    // console.log('antes:', this.preguntasAgregadas)
    // this.preguntasAgregadas = JSON.parse(localStorage.getItem('preguntasEst'));
    // console.log('traer: ',this.preguntasAgregadas);
    // this.preguntasAgregadas.push(this.preguntaEst);
    // console.log('agregar: ', this.preguntasAgregadas);
    // localStorage.setItem('preguntasEst',  JSON.stringify (this.preguntasAgregadas))
    this.dialogRef.close();
  }
}
