import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadosClienteComponent } from './resultados-cliente.component';

describe('ResultadosClienteComponent', () => {
  let component: ResultadosClienteComponent;
  let fixture: ComponentFixture<ResultadosClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ResultadosClienteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultadosClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
