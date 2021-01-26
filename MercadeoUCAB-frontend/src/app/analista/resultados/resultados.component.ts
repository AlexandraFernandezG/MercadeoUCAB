import { JsonpClientBackend } from '@angular/common/http';
import { AfterViewInit, Component, ElementRef, OnInit, QueryList, ViewChild, ViewChildren } from '@angular/core';
import { _countGroupLabelsBeforeOption } from '@angular/material/core';
import { getMatFormFieldDuplicatedHintError } from '@angular/material/form-field';
import { Chart } from 'node_modules/chart.js';
import { ResultadosService } from 'src/app/servicios/resultados.service';
import { isLabeledStatement } from 'typescript';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit, AfterViewInit {

  @ViewChildren('myChart') ctx: QueryList<ElementRef>;
  private id: string;
  public totalRespuesta: any;
  public preguntas: any = Array(0);
  public labels: any = Array(0);
  public respuestas: any = Array(0);
  public arrayresp: any = Array(0);
  public cantidad: any = Array(0);
  chart: any;
  myChart: any;
  myctx: any;
  canva: any;
  data: JSON;


  constructor(
    private service: ResultadosService,
  ) { }

  ngOnInit() {
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
    var objeto = this.getResultados();

    objeto["Preguntas"].forEach(function callback(element, i) {
      element["resultado"].forEach(function result(opcion, j) {
        opcion.forEach(function item(resp,k) {
          if (typeof resp === 'string') {

            this.labels.push(resp);
          }
          else {
            this.respuestas.push(resp);
          }
        }, this);
        this.cantidad.push(this.k);
      }, this);
      this.preguntas.push(element.pregunta);
      // this.Graficas(this.labels, this.data);
      console.log(this.preguntas);

    }, this);
    console.log(this.labels);
    console.log(this.respuestas);
  }

  ConstruirLabel(numPre: number, cant: number){
    let preg: any = Array(0);
    while (cant > 0){
      preg.push(this.labels[numPre]);
      numPre++;
      cant--;
    }
    console.log(preg);
    return preg;
  }

  ConstruirResp(numPre: number, cant: number) {
    let res: any = Array(0);
    while (cant > 0){
      res.push(this.respuestas[numPre]);
      numPre++;
      cant--;
    }
    console.log(res);
    return res;
  }
  ngAfterViewInit() {
    var i = 2;
    console.log('labels', this.labels[i]);
    console.log('respuestas', this.respuestas[i]);

    var ctx = 'myChart';
    this.ctx.forEach((e, i) => {
    this.myChart = new Chart(e.nativeElement.getContext('2d'),{
      type: 'pie',
      data: {
        labels: this.ConstruirLabel(i, 2),
        datasets: [{
          label: '# of Votes',
          data: this.ConstruirResp(i, 2),
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