import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PreguntasSugeridasComponent } from './preguntas-sugeridas.component';

describe('PreguntasSugeridasComponent', () => {
  let component: PreguntasSugeridasComponent;
  let fixture: ComponentFixture<PreguntasSugeridasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PreguntasSugeridasComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PreguntasSugeridasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
