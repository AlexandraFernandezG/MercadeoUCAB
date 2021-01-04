import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstudiosAnalistaComponent } from './estudios-analista.component';

describe('EstudiosAnalistaComponent', () => {
  let component: EstudiosAnalistaComponent;
  let fixture: ComponentFixture<EstudiosAnalistaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstudiosAnalistaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EstudiosAnalistaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
