import { JsonpClientBackend } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { _countGroupLabelsBeforeOption } from '@angular/material/core';
import { getMatFormFieldDuplicatedHintError } from '@angular/material/form-field';
import { Chart } from 'node_modules/chart.js';
import { ResultadosService } from 'src/app/servicios/resultados.service';
import { isLabeledStatement } from 'typescript';
import { map } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Estudio2 } from 'src/app/modelos/estudio';
import { stringify } from '@angular/compiler/src/util';

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit {

  @ViewChildren('myChart') ctx: QueryList<ElementRef>;

  public totalRespuesta: any;
  public preguntas: any = Array(0);
  public labels: any = Array(0);
  public respuestas: any = Array(0);
  public arrayresp: any = Array(0);
  public cantidad: any = Array(0);
  public inicio: any = Array(0);
  idEstudio: number;
  datosCargados: any;
  chart: any;
  myChart: any;
  myctx: any;
  canva: any;
  data: JSON;
  objeto = [];
  respuesta: string; 
  estudio: Estudio2[];


  constructor(
    private service: ResultadosService,
    public actRoute: ActivatedRoute,
    private fb: FormBuilder
  ) { 


  this.respuestaForm = this.fb.group({
    respuesta: new FormControl('',[ Validators.required, Validators.maxLength(500)])
  });
  }

  respuestaForm: FormGroup;
  ngOnInit() {
    this.idEstudio = +this.actRoute.snapshot.paramMap.get("id");
    //this.Resultados();
      setTimeout(() => {
          this.Graficas();
       },3000);

     this.Resultados();
   // this.Graficas();

  }

  /*Para probar desde front
  getResultados() {
    var data = {
      "Preguntas": [
        {
          "pregunta": "Las pizzas fueron inventadas en Rusia?",
          "tipo_pregunta": "Verdadero o Falso",
          "resultado": [
            [
              "Verdadero",
              1,
              "Falso",
              1
            ]
          ]
        },
        {
          "pregunta": "Las pizzas siempre debe llevar salsa?",
          "tipo_pregunta": "Verdadero o Falso",
          "resultado": [
            [
              "Verdadero",
              1,
              "Falso",
              1
            ]
          ]
        },
        {
          "pregunta": "Que opcion crees que es la mas rara para la pizza?",
          "tipo_pregunta": "Selección Simple",
          "resultado": [
            [
              "Pescado",
              2
            ],
            [
              "Arandanos",
              0
            ],
            [
              "Manzana",
              1
            ]
          ]
        },
        {
          "pregunta": "Que Opinas sobre la pizza?",
          "tipo_pregunta": "Selección Simple",
          "resultado": [
            [
              "Buena",
              1
            ],
            [
              "Mala",
              1
            ]
          ]
        },
        {
          "pregunta": "Elije cuales opciones te gustan mas para la pizza?",
          "tipo_pregunta": "Selección Múltiple",
          "resultado": [
            [
              "Pepperoni",
              2
            ],
            [
              "Hongos",
              1
            ],
            [
              "Aceitunas",
              1
            ],
            [
              "Piña",
              0
            ]
          ]
        },
        {
          "pregunta": "Del uno al cinco que te parece las pizzas raras?",
          "tipo_pregunta": "Escala",
          "resultado": [
            [
              "1",
              2,
              "2",
              0,
              "3",
              0,
              "4",
              2,
              "5",
              1
            ]
          ]
        }
      ]
    }
    return data;

  }*/


  Resultados() {

    this.service.getResultados(this.idEstudio).subscribe(Pregunta => {
      console.log("Data:", Pregunta);
      this.objeto = Pregunta;
      console.log(this.objeto["Preguntas"]);
      var k = 0, l = 0;
      this.inicio.push(l);
      this.objeto["Preguntas"].forEach(function callback(element, i) {
        console.log(element["resultado"]);
        element["resultado"].forEach(function result(opcion, j) {
          opcion.forEach(function item(resp) {
            if (typeof resp === 'string') {

              this.labels.push(resp);
              k++;
              l++;
            }
            else {
              this.respuestas.push(resp);
            }
          }, this);
        }, this);
        this.cantidad.push(k);
        this.inicio.push(l);
        k = 0;
        this.preguntas.push(element.pregunta);
        // this.Graficas(this.labels, this.data);
        console.log(this.preguntas);

      }, this);
      console.log(this.labels);
      console.log(this.respuestas);
      console.log(this.cantidad);
      console.log(this.inicio);


    });
  }

  ConstruirLabel(numPre: number, cant: number, inicio: number) {
    let preg: any = Array(0);
    while (cant > 0) {
      preg.push(this.labels[inicio]);
      inicio++;
      cant--;
    }
    console.log(preg);
    return preg;
  }

  ConstruirResp(numPre: number, cant: number, inicio: number) {
    let res: any = Array(0);
    while (cant > 0) {
      res.push(this.respuestas[inicio]);
      inicio++;
      cant--;
    }
    console.log(res);
    return res;
  }
  Graficas() {
    //  var i = 2;
    // console.log('labels', this.labels[i]);
    // console.log('respuestas', this.respuestas[i]);
    console.log("entree");
    var cont = 0;
    var ctx = 'myChart';
    this.ctx.forEach((e, i) => {
      this.myChart = new Chart(e.nativeElement.getContext('2d'), {
        type: 'pie',
        data: {
          labels: this.ConstruirLabel(i, this.cantidad[i], this.inicio[i]),
          datasets: [{
            label: '# of Votes',
            data: this.ConstruirResp(i, this.cantidad[i], this.inicio[i]),
            backgroundColor: [
              'rgba(255, 99, 132, 0.2)',
              'rgba(54, 162, 235, 0.2)',
              'rgba(255, 206, 86, 0.2)',
              'rgba(75, 192, 192, 0.2)',
              'rgba(153, 102, 255, 0.2)',
              'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
              'rgba(255, 99, 132, 1)',
              'rgba(54, 162, 235, 1)',
              'rgba(255, 206, 86, 1)',
              'rgba(75, 192, 192, 1)',
              'rgba(153, 102, 255, 1)',
              'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
          }]
        },
        options: {
          scales: {
            yAxes: [{
              ticks: {
                beginAtZero: true
              }
            }]
          }
        }
      });
    });
  }

  /*enviarRespuesta(){
    this.respuesta=this.respuestaForm.value.respuesta;
    this.service.sendResultados(this.respuesta,this.idEstudio).subscribe(
      Data => { this.respuesta = Data ,
        console.log(this.respuesta)},
      );
    console.log(this.respuestaForm.value.respuesta);
    
  }*/
  enviarRespuesta(): void{
    this.respuesta=this.respuestaForm.value.respuesta;
    let nombre,tipoInstrumento, fechaInicio, fechaFin, estatus, estado, usuarioDto, solicitudEstudioDto
    const editEdu: Estudio2 = {
      id:this.idEstudio, 
      nombre:'',
      tipoInstrumento:'', 
      fechaInicio:null, 
      fechaFin:null, 
      estatus:'', 
      estado:'', 
      observaciones:this.respuesta,
      usuarioDto:1, 
      solicitudEstudioDto:1
    };
    this.service.sendResultados(editEdu).subscribe();
      }


}