import { JsonpClientBackend } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { _countGroupLabelsBeforeOption } from '@angular/material/core';
import { getMatFormFieldDuplicatedHintError } from '@angular/material/form-field';
import { Chart } from 'node_modules/chart.js';
import { ResultadosService } from 'src/app/servicios/resultados.service';
import { isLabeledStatement } from 'typescript';
import { map } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit, AfterViewInit {

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
  objeto :JSON;


  constructor(
    private service: ResultadosService,
    public actRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.idEstudio = +this.actRoute.snapshot.paramMap.get("id");
    this.Resultados();

  }
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

  }


  Resultados() {
    console.log(this.objeto);

    var k = 0, l = 0;
    this.inicio.push(l);
    this.objeto["Preguntas"].forEach(function callback(element, i) {
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
  ngAfterViewInit() {
    //  var i = 2;
    // console.log('labels', this.labels[i]);
    // console.log('respuestas', this.respuestas[i]);
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

}