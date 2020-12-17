import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditPresentacionComponent } from './edit-presentacion.component';

describe('EditPresentacionComponent', () => {
  let component: EditPresentacionComponent;
  let fixture: ComponentFixture<EditPresentacionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditPresentacionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditPresentacionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
