import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MarcaComponent } from './admin/marca/marca.component';
import { CategoriaComponent } from './admin/categoria/categoria.component';
import { SubcategoriaComponent } from './admin/subcategoria/subcategoria.component';
import { UsuarioComponent } from './admin/usuario/usuario.component';
import { AdminComponent } from './admin/admin.component';
import { EstudiosClienteComponent } from './cliente/estudios-cliente/estudios-cliente.component';
import { SolicitudEstudioComponent } from './cliente/solicitud-estudio/solicitud-estudio.component';
import { ClienteComponent } from './cliente/cliente.component';
import { EstudiosEncuestadoComponent } from './encuestado/estudios-encuestado/estudios-encuestado.component';
import { AnalistaComponent } from './analista/analista.component';
import { EstudiosAnalistaComponent } from './analista/estudios-analista/estudios-analista.component';
import { CrearEstudioComponent } from './analista/crear-estudio/crear-estudio.component';
import { TipoComponent } from './admin/tipo/tipo.component';
import { PresentacionComponent } from './admin/presentacion/presentacion.component';
import { RegistroEncuestadoComponent } from './encuestado/registro-encuestado/registro-encuestado.component';

import { ProductoComponent } from './cliente/producto/producto.component';
import { RecuperarContrasenaComponent } from './recuperar-contrasena/recuperar-contrasena.component';
import { CambioContrasenaComponent } from './cambio-contrasena/cambio-contrasena.component';
import { PreguntaComponent } from './admin/pregunta/pregunta.component';
import { RespuestasEncuestaComponent } from './encuestado/respuestas-encuesta/respuestas-encuesta.component';
import { EstudiosComponent } from './admin/estudios/estudios.component';
import { PreguntasEstudioComponent } from './admin/estudios/preguntas-estudio/preguntas-estudio.component';
import { AnalistaPoblacionComponent } from './analista/analista-poblacion/analista-poblacion.component';
import { PreguntasSugeridasComponent } from './admin/preguntas-sugeridas/preguntas-sugeridas.component';
import { ResultadosComponent } from './cliente/resultados/resultados.component';
import { EncuestadoComponent } from './encuestado/encuestado.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'dashboard/marcas', component: MarcaComponent},
  { path: 'admin/categorias', component: CategoriaComponent},
  { path: 'admin/marcas', component: MarcaComponent},
  { path: 'admin/tipos', component: TipoComponent},
  { path: 'admin/presentaciones', component: PresentacionComponent},
  { path: 'admin/usuarios', component: UsuarioComponent},
  { path: 'admin/subcategorias', component: SubcategoriaComponent},
  { path: 'admin/preguntas', component: PreguntaComponent},
  { path: 'admin/preguntas/:id', component: PreguntaComponent},
  { path: 'admin/preguntassugeridas/:id', component: PreguntasSugeridasComponent},
  { path: 'admin/estudios', component: EstudiosComponent},
  { path: 'admin/asignacionpreguntas/:id', component: PreguntasEstudioComponent},
  { path: 'dashboard/usuarios', component: UsuarioComponent},
  { path: 'admin', component: AdminComponent },
  { path: 'cliente', component: ClienteComponent },
  { path: 'cliente/estudios', component: EstudiosClienteComponent },
  { path: 'cliente/solicitar_estudio', component: SolicitudEstudioComponent },
  { path: 'cliente/producto', component: ProductoComponent },
  { path: 'cliente/resultados', component: ResultadosComponent },
  { path: 'encuestado', component: EncuestadoComponent },
  { path: 'encuestado/estudios', component: EstudiosEncuestadoComponent },
  { path: 'encuestado/registro-encuestado', component: RegistroEncuestadoComponent },
  { path: 'encuestado/respuestas-encuesta/:id', component: RespuestasEncuestaComponent },
  { path: 'analista', component: AnalistaComponent },
  { path: 'analista/solicitudes', component: EstudiosAnalistaComponent },
  { path: 'analista/crearEncuesta', component: CrearEstudioComponent },
  { path: 'analista/poblacion', component: AnalistaPoblacionComponent },
  { path: 'recuperarContrasena', component: RecuperarContrasenaComponent},
  { path: 'cambioContrasena', component:  CambioContrasenaComponent},

  { path: '', redirectTo: 'login', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
