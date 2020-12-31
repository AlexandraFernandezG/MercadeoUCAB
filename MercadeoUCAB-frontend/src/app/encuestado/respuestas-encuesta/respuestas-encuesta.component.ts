import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {MatInputModule} from '@angular/material/input'; 

@Component({
  selector: 'app-respuestas-encuesta',
  templateUrl: './respuestas-encuesta.component.html',
  styleUrls: ['./respuestas-encuesta.component.css']
})
export class RespuestasEncuestaComponent implements OnInit {

  constructor() {
   }
  ngOnInit(): void {

  }
  favoriteSeason: string;
  seasons: string[] = ['Invierno', 'Primavera', 'Verano', 'Otoño'];

}
