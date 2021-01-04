import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstudiosEncuestadoComponent } from './estudios-encuestado.component';

describe('EstudiosEncuestadoComponent', () => {
  let component: EstudiosEncuestadoComponent;
  let fixture: ComponentFixture<EstudiosEncuestadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstudiosEncuestadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EstudiosEncuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
