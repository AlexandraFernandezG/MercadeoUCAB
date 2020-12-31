import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreguntasEstudioComponent } from './preguntas-estudio.component';

describe('PreguntasEstudioComponent', () => {
  let component: PreguntasEstudioComponent;
  let fixture: ComponentFixture<PreguntasEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreguntasEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreguntasEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
