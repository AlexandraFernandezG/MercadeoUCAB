import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoencuestadoComponent } from './nuevoencuestado.component';

describe('NuevoencuestadoComponent', () => {
  let component: NuevoencuestadoComponent;
  let fixture: ComponentFixture<NuevoencuestadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevoencuestadoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevoencuestadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
