import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEncuestadoEstudioComponent } from './add-encuestado-estudio.component';

describe('AddEncuestadoEstudioComponent', () => {
  let component: AddEncuestadoEstudioComponent;
  let fixture: ComponentFixture<AddEncuestadoEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddEncuestadoEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEncuestadoEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
