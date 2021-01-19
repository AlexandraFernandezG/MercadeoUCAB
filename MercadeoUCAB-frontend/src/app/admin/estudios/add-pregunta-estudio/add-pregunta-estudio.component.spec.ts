import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPreguntaEstudioComponent } from './add-pregunta-estudio.component';

describe('AddPreguntaEstudioComponent', () => {
  let component: AddPreguntaEstudioComponent;
  let fixture: ComponentFixture<AddPreguntaEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPreguntaEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPreguntaEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
