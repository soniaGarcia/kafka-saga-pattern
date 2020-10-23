import { Routes  } from '@angular/router';
import { AppComponent } from '../first/app.component';
import { AppComponent1 } from '../second/app.component';
import { AppComponent2 } from '../third/app.component';

export const routes: Routes  = [
    { path: 'app-component', component: AppComponent },
    { path: 'second-component', component: AppComponent1 },
    { path: 'third-component', component: AppComponent2 }
];