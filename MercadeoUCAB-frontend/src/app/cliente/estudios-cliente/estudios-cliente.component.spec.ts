import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstudiosClienteComponent } from './estudios-cliente.component';

describe('EstudiosClienteComponent', () => {
  let component: EstudiosClienteComponent;
  let fixture: ComponentFixture<EstudiosClienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EstudiosClienteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EstudiosClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
