import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

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
import { MarcaComponent } from './admin/marca/marca.component';
import { EditMarcaComponent } from './admin/marca/edit-marca/edit-marca.component';
import { SubcategoriaComponent } from './admin/subcategoria/subcategoria.component';
import { CategoriaComponent } from './admin/categoria/categoria.component';
import { UsuarioComponent } from './admin/usuario/usuario.component';
import { AddSubcategoriaComponent } from './admin/subcategoria/add-subcategoria/add-subcategoria.component';
import { EditSubcategoriaComponent } from './admin/subcategoria/edit-subcategoria/edit-subcategoria.component';
import { AddCategoriaComponent } from './admin/categoria/add-categoria/add-categoria.component';
import { EditCategoriaComponent } from './admin/categoria/edit-categoria/edit-categoria.component';
import { AddUsuarioComponent } from './admin/usuario/add-usuario/add-usuario.component';
import { EditUsuarioComponent } from './admin/usuario/edit-usuario/edit-usuario.component';
import { AdminComponent } from './admin/admin.component';
import { RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { EstudiosClienteComponent } from './cliente/estudios-cliente/estudios-cliente.component';
import { SolicitudEstudioComponent } from './cliente/solicitud-estudio/solicitud-estudio.component';
import { EncuestadoComponent } from './encuestado/encuestado.component';
import { EstudiosEncuestadoComponent } from './encuestado/estudios-encuestado/estudios-encuestado.component';
import { RespuestasEncuestaComponent } from './encuestado/respuestas-encuesta/respuestas-encuesta.component';
import { RegistroEncuestadoComponent } from './registro-encuestado/registro-encuestado.component';
import { AnalistaComponent } from './analista/analista.component';
import { EstudiosAnalistaComponent } from './analista/estudios-analista/estudios-analista.component';
import { DataEstudioComponent } from './analista/data-estudio/data-estudio.component';

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
    AdminComponent,
    ClienteComponent,
    EstudiosClienteComponent,
    SolicitudEstudioComponent,
    EncuestadoComponent,
    EstudiosEncuestadoComponent,
    RespuestasEncuestaComponent,
    RegistroEncuestadoComponent,
    AnalistaComponent,
    EstudiosAnalistaComponent,
    DataEstudioComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    ReactiveFormsModule,
    NgbModule,
    FontAwesomeModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
