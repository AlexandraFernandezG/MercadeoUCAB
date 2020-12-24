import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPreguntaComponent } from './edit-pregunta.component';

describe('EditPreguntaComponent', () => {
  let component: EditPreguntaComponent;
  let fixture: ComponentFixture<EditPreguntaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditPreguntaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPreguntaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
