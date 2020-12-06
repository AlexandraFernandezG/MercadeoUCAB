import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './login/login.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MarcaComponent } from './marca/marca.component';
import { EditMarcaComponent } from './marca/edit-marca/edit-marca.component';
import { SubcategoriaComponent } from './subcategoria/subcategoria.component';
import { CategoriaComponent } from './categoria/categoria.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { AddSubcategoriaComponent } from './subcategoria/add-subcategoria/add-subcategoria.component';
import { EditSubcategoriaComponent } from './subcategoria/edit-subcategoria/edit-subcategoria.component';
import { AddCategoriaComponent } from './categoria/add-categoria/add-categoria.component';
import { EditCategoriaComponent } from './categoria/edit-categoria/edit-categoria.component';
import { AddUsuarioComponent } from './usuario/add-usuario/add-usuario.component';
import { EditUsuarioComponent } from './usuario/edit-usuario/edit-usuario.component';
import { PreguntaComponent } from './pregunta/pregunta.component';
import { AddPreguntaComponent } from './pregunta/add-pregunta/add-pregunta.component';
import { EditPreguntaComponent } from './pregunta/edit-pregunta/edit-pregunta.component';
import { EncuestaComponent } from './encuesta/encuesta.component';
import { EstudioComponent } from './estudio/estudio.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    MarcaComponent,
    EditMarcaComponent,
    SubcategoriaComponent,
    CategoriaComponent,
    UsuarioComponent,
    AddSubcategoriaComponent,
    EditSubcategoriaComponent,
    AddCategoriaComponent,
    EditCategoriaComponent,
    AddUsuarioComponent,
    EditUsuarioComponent,
    PreguntaComponent,
    AddPreguntaComponent,
    EditPreguntaComponent,
    EncuestaComponent,
    EstudioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    NgbModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
