import { TestBed, async } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent1 } from './app.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
      declarations: [
        AppComponent1
      ],
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent1);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'angular-apollo'`, () => {
    const fixture = TestBed.createComponent(AppComponent1);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('angular-apollo');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent1);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.content span').textContent).toContain('angular-apollo app is running!');
  });
});
