import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegistroEncuestadoComponent } from './registro-encuestado.component';

describe('RegistroEncuestadoComponent', () => {
  let component: RegistroEncuestadoComponent;
  let fixture: ComponentFixture<RegistroEncuestadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistroEncuestadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistroEncuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
