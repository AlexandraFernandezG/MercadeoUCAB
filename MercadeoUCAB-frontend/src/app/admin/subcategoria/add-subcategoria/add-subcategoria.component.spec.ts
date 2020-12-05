import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddSubcategoriaComponent } from './add-subcategoria.component';

describe('AddSubcategoriaComponent', () => {
  let component: AddSubcategoriaComponent;
  let fixture: ComponentFixture<AddSubcategoriaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddSubcategoriaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddSubcategoriaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
