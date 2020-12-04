import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataEstudioComponent } from './data-estudio.component';

describe('DataEstudioComponent', () => {
  let component: DataEstudioComponent;
  let fixture: ComponentFixture<DataEstudioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataEstudioComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DataEstudioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
