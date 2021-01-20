import { Component, OnInit } from '@angular/core';
import { Chart } from 'node_modules/chart.js';

@Component({
  selector: 'app-resultados',
  templateUrl: './resultados.component.html',
  styleUrls: ['./resultados.component.css']
})
export class ResultadosComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
    
    
  var verdaderoFalsoChart = new Chart("VerdaderoFalso", {
    type: 'pie',
    data: {
      labels: ['Verdadero', 'Falso'],
      datasets: [{
        label: 'Pocentaje de cada uno',
        data: [25,75],
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(54, 162, 235, 0.2)'
        ],
        borderColor: [
          'rgba(255, 99, 132, 1)',
          'rgba(54, 162, 235, 1)'
        ],
        borderWidth: 1
      }]
    },
  });
  }

}

