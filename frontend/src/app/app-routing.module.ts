import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { RecipeListComponent } from './recipe/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './recipe/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/recipe-form/recipe-form.component';
import { CommentListComponent } from './comment/comment-list/comment-list.component';
import { CommentFormComponent } from './comment/comment-form/comment-form.component';
import { Error404Component } from './error404/error404.component';
import { GuardModule } from '../guard/guard.module';
import { RecipeCategoriesComponent } from './recipe/recipe-categories/recipe-categories.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { RecipeFavoritesComponent } from './recipe/recipe-favorites/recipe-favorites.component';

const routes: Routes = [
  { path: '', component: RecipeListComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'users', component: UserListComponent, canActivate: [GuardModule] },
  { path: 'users/:id', component: UserDetailComponent, canActivate: [GuardModule], canActivateChild: [GuardModule] },
  { path: 'profile', component: UserProfileComponent, canActivate: [GuardModule] },
  { path: 'recipes', component: RecipeListComponent, canActivate: [GuardModule] },
  { path: 'recipes/new', component: RecipeFormComponent, canActivate: [GuardModule] },
  { path: 'recipes/:id', component: RecipeDetailComponent, canActivate: [GuardModule] },
  { path: 'categories', component: RecipeCategoriesComponent, canActivate: [GuardModule] },
  { path: 'favorites', component: RecipeFavoritesComponent, canActivate: [GuardModule] },
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
