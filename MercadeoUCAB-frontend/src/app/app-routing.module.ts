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

const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'dashboard/marcas', component: MarcaComponent},
  { path: 'admin/categorias', component: CategoriaComponent},
  { path: 'dashboard/subcategorias', component: SubcategoriaComponent},
  { path: 'dashboard/usuarios', component: UsuarioComponent},
  { path: 'admin', component: AdminComponent },
  { path: 'cliente', component: ClienteComponent },
  { path: 'cliente/estudios', component: EstudiosClienteComponent },
  { path: 'cliente/solicitar_estudio', component: SolicitudEstudioComponent },

  { path: '', redirectTo: 'categorias', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
