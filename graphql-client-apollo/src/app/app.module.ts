import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './first/app.component';
import { AppComponent1 } from './second/app.component';
import { AppComponent2 } from './third/app.component';
import { MenuComponent } from './menu/menu.component';
import { GraphQLModule } from './graphql.module';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,AppComponent1,AppComponent2,MenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    GraphQLModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [MenuComponent]
})
export class AppModule { }
