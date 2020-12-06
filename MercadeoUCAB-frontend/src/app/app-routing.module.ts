import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { MarcaComponent } from './marca/marca.component';
import { CategoriaComponent } from './categoria/categoria.component';
import { SubcategoriaComponent } from './subcategoria/subcategoria.component';
import { UsuarioComponent } from './usuario/usuario.component';
import { PreguntaComponent } from './pregunta/pregunta.component';
import { AddPreguntaComponent } from './pregunta/add-pregunta/add-pregunta.component';
import { EncuestaComponent } from './encuesta/encuesta.component';
import { EstudioComponent } from './estudio/estudio.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'dashboard/marcas', component: MarcaComponent},
  { path: 'dashboard/categorias', component: CategoriaComponent},
  { path: 'dashboard/subcategorias', component: SubcategoriaComponent},
  { path: 'dashboard/usuarios', component: UsuarioComponent},
  { path: 'dashboard/preguntas', component: PreguntaComponent},
  { path: 'dashboard/preguntas/add', component: AddPreguntaComponent},
  { path: 'dashboard/encuesta', component: EncuestaComponent},
  { path: 'dashboard/estudio', component: EstudioComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
