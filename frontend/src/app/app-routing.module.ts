import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { CommentListComponent } from './comment/comment-list/comment-list.component';
import { CommentFormComponent } from './comment/comment-form/comment-form.component';
import { Error404Component } from './error404/error404.component';
import { GuardModule } from '../guard/guard.module';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { HomeComponent } from './home/home.component';
import { RecipeComponent } from './recipe/recipe.component';



const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'users', component: UserListComponent, canActivate: [GuardModule] },
  { path: 'users/:id', component: UserDetailComponent, canActivate: [GuardModule], canActivateChild: [GuardModule] },
  { path: 'profile', component: UserProfileComponent, canActivate: [GuardModule] },
  { path: 'recipes', component: RecipeComponent },
  { path: 'recipes/new', component: RecipeComponent },
  { path: 'recipes/:id', component: RecipeComponent },
  { path: 'comments', component: CommentListComponent, canActivate: [GuardModule] },
  { path: 'comments/new', component: CommentFormComponent, canActivate: [GuardModule] },
  { path: '404', component: Error404Component },
  { path: '**', redirectTo: '404' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
