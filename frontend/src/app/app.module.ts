import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { UserListComponent } from './user/user-list/user-list.component';
import { UserDetailComponent } from './user/user-detail/user-detail.component';
import { UserAvatarComponent } from './user/user-avatar/user-avatar.component';
import { RecipeListComponent } from './recipe/recipe-list/recipe-list.component';
import { RecipeDetailComponent } from './recipe/recipe-detail/recipe-detail.component';
import { RecipeFormComponent } from './recipe/recipe-form/recipe-form.component';
import { CommentListComponent } from './comment/comment-list/comment-list.component';
import { CommentFormComponent } from './comment/comment-form/comment-form.component';
import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { Error404Component } from './error404/error404.component';
import { NavbarComponent } from './main-component/navbar/navbar.component';
import { FooterComponent } from './main-component/footer/footer.component';
import { JwtInterceptorService } from './interceptors/jwt-interceptor.service';
import { GuardModule } from '../guard/guard.module';
import { FormsModule } from '@angular/forms';
import { CoreModule } from './core/core.module';
import { RouterModule } from '@angular/router';
import { SharedModule } from './shared/shared.module';
import { RecipeCategoriesComponent } from './recipe/recipe-categories/recipe-categories.component';
import { UserProfileComponent } from './user/user-profile/user-profile.component';
import { RecipeFavoritesComponent } from './recipe/recipe-favorites/recipe-favorites.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    UserListComponent,
    UserDetailComponent,
    UserAvatarComponent,
    RecipeListComponent,
    RecipeDetailComponent,
    RecipeFormComponent,
    CommentListComponent,
    CommentFormComponent,
    Error404Component,
    NavbarComponent,
    FooterComponent,
    RecipeCategoriesComponent,
    UserProfileComponent,
    RecipeFavoritesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    RouterModule,
    GuardModule,
    CoreModule,
    SharedModule,
    FormsModule
  ],
  providers: [
    provideClientHydration(),
    provideHttpClient(withInterceptorsFromDi()),
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptorService, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
