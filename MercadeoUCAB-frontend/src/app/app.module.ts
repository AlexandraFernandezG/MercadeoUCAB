import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule} from '@angular/material/checkbox';
import { MatExpansionModule} from '@angular/material/expansion';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner'; 
import { MatSelectModule } from '@angular/material/select';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { MarcaComponent } from './admin/marca/marca.component';
import { AddMarcaComponent } from './admin/marca/add-marca/add-marca.component';
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
import { TipoComponent } from './admin/tipo/tipo.component';
import { AddTipoComponent } from './admin/tipo/add-tipo/add-tipo.component';
import { EditTipoComponent } from './admin/tipo/edit-tipo/edit-tipo.component';
import { PresentacionComponent } from './admin/presentacion/presentacion.component';
import { AddPresentacionComponent } from './admin/presentacion/add-presentacion/add-presentacion.component';
import { EditPresentacionComponent } from './admin/presentacion/edit-presentacion/edit-presentacion.component';
import { RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { EstudiosClienteComponent } from './cliente/estudios-cliente/estudios-cliente.component';
import { SolicitudEstudioComponent } from './cliente/solicitud-estudio/solicitud-estudio.component';
import { EncuestadoComponent } from './encuestado/encuestado.component';
import { EstudiosEncuestadoComponent } from './encuestado/estudios-encuestado/estudios-encuestado.component';
import { RespuestasEncuestaComponent } from './encuestado/respuestas-encuesta/respuestas-encuesta.component';
import { RegistroEncuestadoComponent } from './encuestado/registro-encuestado/registro-encuestado.component';
import { AnalistaComponent } from './analista/analista.component';
import { EstudiosAnalistaComponent } from './analista/estudios-analista/estudios-analista.component';
import { DataEstudioComponent } from './analista/data-estudio/data-estudio.component';
import { CategoriasService } from './servicios/categorias.service';
import { CrearEstudioComponent } from './analista/crear-estudio/crear-estudio.component';
import { SubcategoriasService } from './servicios/subcategorias.service';
import { MarcasService } from './servicios/marcas.service';
import { TiposService } from './servicios/tipos.service';
import { PresentacionesService } from './servicios/presentaciones.service';
import { UsuariosService } from './servicios/usuarios.service';
import { ProductoComponent } from './cliente/producto/producto.component';
import { AddProductoComponent } from './cliente/producto/add-producto/add-producto.component';
import { ProductosService } from './servicios/productos.service';
import { RecuperarContrasenaComponent } from './recuperar-contrasena/recuperar-contrasena.component';
import { CambioContrasenaComponent } from './cambio-contrasena/cambio-contrasena.component';
import { PreguntaComponent } from './admin/pregunta/pregunta.component';
import { AddPreguntaComponent } from './admin/pregunta/add-pregunta/add-pregunta.component';
import { EditPreguntaComponent } from './admin/pregunta/edit-pregunta/edit-pregunta.component';
import { PreguntasService } from './servicios/preguntas.service';
import { EstudiosComponent } from './admin/estudios/estudios.component';
import { AddEstudioComponent } from './admin/estudios/add-estudio/add-estudio.component';
import { EditEstudioComponent } from './admin/estudios/edit-estudio/edit-estudio.component';
import { PreguntasEstudioComponent } from './admin/estudios/preguntas-estudio/preguntas-estudio.component';
import { DialogComponent } from './admin/estudios/dialog/dialog.component';
import { AnalistaPoblacionComponent } from './analista/analista-poblacion/analista-poblacion.component';
import { EditarSolicitudComponent } from './analista/editar-solicitud/editar-solicitud.component';
import { PreguntasSugeridasComponent } from './admin/preguntas-sugeridas/preguntas-sugeridas.component';
import { ResultadosComponent } from './cliente/resultados/resultados.component';
import { SolicitudesComponent } from './admin/solicitudes/solicitudes.component';
import { SolicitudEstudiosService } from './servicios/solicitud-estudios.service';
import { EstudiosService } from './servicios/estudios.service';
import { EstudiosSugeridosComponent } from './admin/estudios-sugeridos/estudios-sugeridos.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    MarcaComponent,
    AddMarcaComponent,
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
    AnalistaComponent,
    EstudiosAnalistaComponent,
    DataEstudioComponent,
    CrearEstudioComponent,
    TipoComponent,
    PresentacionComponent,
    AddTipoComponent,
    EditTipoComponent,
    AddPresentacionComponent,
    EditPresentacionComponent,
    UsuarioComponent,
    AddUsuarioComponent,
    EditUsuarioComponent,
    ProductoComponent,
    AddProductoComponent,
    RecuperarContrasenaComponent,
    CambioContrasenaComponent,
    RegistroEncuestadoComponent,
    PreguntaComponent,
    AddPreguntaComponent,
    EditPreguntaComponent,
    EstudiosComponent,
    AddEstudioComponent,
    EditEstudioComponent,
    PreguntasEstudioComponent,
    DialogComponent,
    AnalistaPoblacionComponent,
    EditarSolicitudComponent,
    PreguntasSugeridasComponent,
    ResultadosComponent,
    SolicitudesComponent,
    EstudiosSugeridosComponent
  ],
  imports: [
    RouterModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatDatepickerModule,
    MatInputModule,
    ReactiveFormsModule,
    NgbModule,
    FontAwesomeModule,
    HttpClientModule,
    MatDialogModule,
    MatProgressSpinnerModule,
    MatFormFieldModule,
    MatSelectModule,
    MatTableModule,
    MatPaginatorModule,
    MatRadioModule,
    MatToolbarModule,
    MatCheckboxModule,
    MatGridListModule,
    MatDatepickerModule,
    MatNativeDateModule,
    FormsModule
  ],
  providers: [
    CategoriasService,
    SubcategoriasService,
    MarcasService,
    TiposService,
    PresentacionesService,
    UsuariosService,
    ProductosService,
    PreguntasService,
    SolicitudEstudiosService,
    EstudiosService

  ],
  bootstrap: [AppComponent],
  entryComponents: [
    SolicitudEstudioComponent,
    CategoriaComponent,
    MatDatepickerModule,
    MatNativeDateModule
    ]
})
export class AppModule { }
