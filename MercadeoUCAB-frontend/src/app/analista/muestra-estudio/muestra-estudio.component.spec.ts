import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MuestraEstudioComponent } from './muestra-estudio.component';

describe('MuestraEstudioComponent', () => {
  let component: MuestraEstudioComponent;
  let fixture: ComponentFixture<MuestraEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MuestraEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MuestraEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
